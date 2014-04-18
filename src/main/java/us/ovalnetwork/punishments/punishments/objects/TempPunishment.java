package us.ovalnetwork.punishments.punishments.objects;

import us.ovalnetwork.punishments.OvalPunishments;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * created by codermason on 4/3/14
 */
public abstract class TempPunishment extends Punishment {

    private Date until;

    public TempPunishment(UUID punished, String punisher, String reason, Date until) {
        super(punished, punisher, reason);
        this.until = until;
    }

    public Date getUntil() {
        return this.until;
    }

    public boolean isOver() {
        return getUntil().before(new Date());
    }

    public boolean saveToDB() {
        try {
            String uuid = getPunished().toString().replace("-", ""), punisher = getPunisher(), reason = getReason(), type = getType(), date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getUntil());
            OvalPunishments.get().getAPI().getSQL().execute("INSERT INTO punishments(uuid, punisher, reason, type, until) VALUES ('"+uuid+"', '"+punisher+"', '"+reason+"', '"+type+"', '"+date+"')");
            return true;
        }catch(Exception e) {
            return false;
        }
    }

}
