package us.ovalnetwork.punishments.punishments.temps;

import net.md_5.bungee.api.ChatColor;
import us.ovalnetwork.punishments.punishments.objects.TempPunishment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * created by codermason on 4/3/14
 */
public class TempMute extends TempPunishment {

    public TempMute(UUID punished, String punisher, String reason, Date until) {
        super(punished, punisher, reason, until);
    }

    public String getType() {
        return "tempmute";
    }

    public String getMessage() {
        return ChatColor.BOLD + "" + ChatColor.YELLOW + "MUTED BY: " + ChatColor.RESET + "" + getPunisher() + "\n" +
               ChatColor.BOLD + "" + ChatColor.YELLOW + "REASON: " + ChatColor.RESET + "" + getReason() + "\n" +
               ChatColor.BOLD + "" + ChatColor.YELLOW + "EXPIRES: " + ChatColor.RESET + "" + new SimpleDateFormat("MM/dd/yyyy hh:mm a").format(getUntil());
    }

}
