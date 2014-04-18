package us.ovalnetwork.punishments.punishments;

import net.md_5.bungee.api.ChatColor;
import us.ovalnetwork.punishments.punishments.objects.Punishment;

import java.util.UUID;

/**
 * created by codermason on 4/3/14
 */
public class Kick extends Punishment {

    public Kick(UUID punished, String punisher, String reason) {
        super(punished, punisher, reason);
    }

    public String getType() {
        return "kick";
    }

    public String getMessage() {
        return ChatColor.RED + "" + ChatColor.BOLD + "KICKED BY: " + ChatColor.RESET + getPunisher()+"\n" +
               ChatColor.RED + "" + ChatColor.BOLD + "REASON: " + ChatColor.RESET + getReason();
    }

}
