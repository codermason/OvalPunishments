package us.ovalnetwork.punishments.punishments.temps;

import net.md_5.bungee.api.ChatColor;
import us.ovalnetwork.punishments.punishments.objects.TempPunishment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * created by codermason on 4/3/14
 */
public class TempBan extends TempPunishment {

    public TempBan(UUID punished, String punisher, String reason, Date until) {
        super(punished, punisher, reason, until);
    }

    public String getType() {
        return "tempban";
    }

    public String getMessage() {
        return ChatColor.RED + "" + ChatColor.BOLD +"BANNED BY: " + ChatColor.RESET + getPunisher()+"\n" +
                ChatColor.RED + "" + ChatColor.BOLD +"REASON: " + ChatColor.RESET + getReason()+"\n" +
                ChatColor.RED + "" + ChatColor.BOLD +"EXPIRES: " + ChatColor.RESET + new SimpleDateFormat("MM/dd/yyyy hh:mm a").format(getUntil());
    }

}
