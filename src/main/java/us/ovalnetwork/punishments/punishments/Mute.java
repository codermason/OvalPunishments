package us.ovalnetwork.punishments.punishments;

import net.md_5.bungee.api.ChatColor;
import us.ovalnetwork.punishments.punishments.objects.Punishment;

import java.util.UUID;

/**
 * created by codermason on 4/3/14
 */
public class Mute extends Punishment {

    public Mute(UUID punished, String punisher, String reason) {
        super(punished, punisher, reason);
    }

    public String getType() {
       return "mute";
    }

    public String getMessage() {
        return ChatColor.BOLD + "" + ChatColor.YELLOW + "MUTED BY: " + ChatColor.RESET + "" + getPunisher() + "\n" +
               ChatColor.BOLD + "" + ChatColor.YELLOW + "REASON: " + ChatColor.RESET + "" + getReason() + "\n" +
               ChatColor.BOLD + "" + ChatColor.YELLOW + "EXPIRES: " + ChatColor.RESET + "NEVER";
    }

}
