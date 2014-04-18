package us.ovalnetwork.punishments.punishments.objects;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import us.ovalnetwork.punishments.OvalPunishments;
import us.ovalnetwork.punishments.events.PlayerAppealEvent;
import us.ovalnetwork.punishments.events.PlayerPunishEvent;

import java.util.UUID;

/**
 * created by codermason on 4/3/14
 */
public abstract class Punishment {

    private String reason, punisher;
    private UUID punished;

    public Punishment(UUID punished, String punisher, String reason) {
        this.punisher = punisher;
        this.reason = reason;
        this.punished = punished;
    }

    public String getPunisher() {
        return this.punisher;
    }

    public UUID getPunished() {
        return this.punished;
    }

    public String getReason() {
        return this.reason;
    }

    public abstract String getType();

    public abstract String getMessage();


    public boolean execute(boolean kick) {
        ProxiedPlayer player = OvalPunishments.get().getManager().getProfile(getPunished()).getPlayer();
        if(player != null) {
            if(kick)
                player.disconnect(getMessage());
            else
                player.sendMessages(getMessage().split("\n"));
        }
        callPunished(getPunished(), this);
        return saveToDB();
    }

    public boolean saveToDB() {
        try {
            String uuid = getPunished().toString().replace("-", ""), punisher = getPunisher(), reason = getReason(), type = getType();
            OvalPunishments.get().getAPI().getSQL().execute("INSERT INTO punishments(`uuid`, `punisher`, `reason`, `type`) VALUES ('"+uuid+"', '"+punisher+"', '"+reason+"', '"+type+"')");
            return true;
        }catch(Exception e) {
            return false;
        }
    }

    public static void callPunished(UUID profile, Punishment punishment) {
        PlayerPunishEvent event = new PlayerPunishEvent(profile, punishment);
        ProxyServer.getInstance().getPluginManager().callEvent(event);
    }

    public static void callAppealed(UUID profile) {
        PlayerAppealEvent event = new PlayerAppealEvent(profile);
        ProxyServer.getInstance().getPluginManager().callEvent(event);
    }

}
