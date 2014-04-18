package us.ovalnetwork.punishments.punishments;

import net.md_5.bungee.api.ChatColor;
import us.ovalnetwork.punishments.punishments.objects.Punishment;

import java.util.UUID;

/**
 * created by codermason on 4/3/14
 */
public class Ban extends Punishment {

    public Ban(UUID punished, String punisher, String reason) {
        super(punished, punisher, reason);
    }

    public String getType() {
        return "ban";
    }

    public String getMessage() {
        return ChatColor.RED + "" + ChatColor.BOLD +"BANNED BY: " + ChatColor.RESET + getPunisher()+"\n" +
               ChatColor.RED + "" + ChatColor.BOLD +"REASON: " + ChatColor.RESET + getReason()+"\n" +
               ChatColor.RED + "" + ChatColor.BOLD +"EXPIRES: " + ChatColor.RESET +"NEVER";
    }

}
