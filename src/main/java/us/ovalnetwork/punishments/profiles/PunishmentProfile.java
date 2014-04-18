package us.ovalnetwork.punishments.profiles;

import us.ovalnetwork.api.profiles.Profile;
import us.ovalnetwork.api.utils.FruitSQL;
import us.ovalnetwork.punishments.OvalPunishments;
import us.ovalnetwork.punishments.punishments.Ban;
import us.ovalnetwork.punishments.punishments.Kick;
import us.ovalnetwork.punishments.punishments.Mute;
import us.ovalnetwork.punishments.punishments.objects.Punishment;
import us.ovalnetwork.punishments.punishments.temps.TempBan;
import us.ovalnetwork.punishments.punishments.temps.TempMute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * created by codermason on 4/3/14
 */
public class PunishmentProfile extends Profile {

    private boolean muted, banned, tempBanned, tempMuted;
    private Mute mute;
    private Ban ban;
    private TempMute tempMute;
    private TempBan tempBan;

    public PunishmentProfile(UUID uuid) {
        super(uuid);
        refresh();
    }

    public boolean ban(String punisher, String reason) {
        return new Ban(getUUID(), punisher, reason).execute(true);
    }

    public boolean tempBan(String punisher, String reason, Date until) {
        return new TempBan(getUUID(), punisher, reason, until).execute(true);
    }

    public boolean kick(String punisher, String reason) {
        return new Kick(getUUID(), punisher, reason).execute(true);
    }

    public boolean mute(String punisher, String reason) {
        return new Mute(getUUID(), punisher, reason).execute(false);
    }

    public boolean tempMute(String punisher, String reason, Date until) {
        return new TempMute(getUUID(), punisher, reason, until).execute(false);
    }

    public boolean unban() {
        if(isBanned() || isTempBanned()) {
            OvalPunishments.get().getAPI().getSQL().execute("DELETE FROM punishments WHERE uuid='"+getUUID().toString().replace("-","")+"' AND type='tempban' OR type='ban'");
            Punishment.callAppealed(getUUID());
            return true;
        }
        return false;
    }

    public boolean unmute() {
        if(isMuted() || isTempMuted()) {
            OvalPunishments.get().getAPI().getSQL().execute("DELETE FROM punishments WHERE uuid='" + getUUID().toString().replace("-", "") + "' AND type='tempmute' OR type='mute'");
            Punishment.callAppealed(getUUID());
            return true;
        }
        return false;
    }

    public boolean isMuted() {
        return this.muted;
    }

    public Mute getMute() {
        return isMuted() ? this.mute : null;
    }

    private void saveMute(ResultSet query) {
        this.muted = true;
        try {
            this.mute = new Mute(getUUID(), query.getString("punisher"), query.getString("reason"));
        }catch(SQLException e) {
            OvalPunishments.get().log("Error when contacting database: "+e.toString());
       }
    }

    public boolean isTempMuted() {
        return this.tempMuted;
    }

    public TempMute getTempMute() {
        return isTempMuted() ? this.tempMute : null;
    }

    private void saveTempMute(ResultSet query) {
        this.tempMuted = true;
        try {
            this.tempMute = new TempMute(getUUID(), query.getString("punisher"), query.getString("reason"), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(query.getString("until")));
        }catch(SQLException e) {
            OvalPunishments.get().log("Error when contacting database: "+e.toString());
        }catch(ParseException e) {
            OvalPunishments.get().log("Error while parsing date: "+e.toString());
        }
    }

    public boolean isBanned() {
        return this.banned;
    }

    public Ban getBan() {
        return isBanned() ? this.ban : null;
    }

    private void saveBan(ResultSet query) {
        this.banned = true;
        try {
            this.ban = new Ban(getUUID(), query.getString("punisher"), query.getString("reason"));
        }catch(SQLException e) {
            OvalPunishments.get().log("Error when contacting database: "+e.toString());
        }
    }

    public boolean isTempBanned() {
        return this.tempBanned;
    }

    public TempBan getTempBan() {
        return isTempBanned() ? this.tempBan : null;
    }

    private void saveTempBan(ResultSet query) {
        this.tempBanned = true;
        try {
            this.tempBan = new TempBan(getUUID(), query.getString("punisher"), query.getString("reason"), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(query.getString("until")));
        }catch(SQLException e) {
            OvalPunishments.get().log("Error when contacting database: "+e.toString());
        }catch(ParseException e) {
            OvalPunishments.get().log("Error while parsing date: "+e.toString());
        }
    }

    public void refresh() {
        FruitSQL sql = OvalPunishments.get().getAPI().getSQL();
        try {
            ResultSet m = sql.executeQuery("SELECT * FROM punishments WHERE uuid='" + getUUID().toString().replace("-", "") + "' AND type='mute'");
            ResultSet tempm = sql.executeQuery("SELECT * FROM punishments WHERE uuid='" + getUUID().toString().replace("-", "") + "' AND type='tempmute'");
            ResultSet b = sql.executeQuery("SELECT * FROM punishments WHERE uuid='" + getUUID().toString().replace("-", "") + "' AND type='ban'");
            ResultSet tempb = sql.executeQuery("SELECT * FROM punishments WHERE uuid='" + getUUID().toString().replace("-", "") + "' AND type='tempban'");

            if(m != null && m.next()) saveMute(m); else this.muted = false;
            if(tempm != null && tempm.next()) saveTempMute(tempm); else this.tempMuted = false;
            if(b != null && b.next()) saveBan(b); else this.banned = false;
            if(tempb != null && tempb.next()) saveTempBan(tempb); else this.tempBanned = false;
        }catch(SQLException e) {
            OvalPunishments.get().log("Error when contacting database: "+e.toString());
        }
    }

}
