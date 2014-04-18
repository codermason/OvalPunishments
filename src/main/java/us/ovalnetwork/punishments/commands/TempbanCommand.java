package us.ovalnetwork.punishments.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import us.ovalnetwork.api.profiles.Profile;
import us.ovalnetwork.api.utils.DateUtils;
import us.ovalnetwork.api.utils.UUIDLibrary;
import us.ovalnetwork.punishments.OvalPunishments;
import us.ovalnetwork.punishments.profiles.PunishmentProfile;

import java.util.Date;

/**
 * created by codermason on 3/31/14
 */
public class TempbanCommand extends Command {

    private OvalPunishments plugin;

    public TempbanCommand(OvalPunishments plugin) {
        super("g:tempban", "oval.punishments.global.commands.tempban", new String[]{});
        this.plugin = plugin;
    }

    public void execute(CommandSender sender, String[] args) {
        if(args.length <= 1) {
            sender.sendMessage(ChatColor.YELLOW+"/g:tempban <player> <time> [reason]");
        }else{
            String target = args[0];
            Profile pt = plugin.getManager().getProfile(UUIDLibrary.getUUID(target));
            if(pt == null) {
                sender.sendMessage(ChatColor.RED+target+" not found.");
                return;
            }else if(((PunishmentProfile)pt).isBanned() || ((PunishmentProfile)pt).isTempBanned()) {
                sender.sendMessage(ChatColor.RED+target+" is already banned.");
                return;
            }
            Date until = DateUtils.parseDate(args[1]);
            if(until == null) {
                sender.sendMessage(ChatColor.RED+args[1]+" is an invalid date format - EX: 5d:2h:1s");
                return;
            }
            String reason = "";
            if(args.length > 1) {
                for(int i=2;i<args.length;i++)
                    reason += args[i] + " ";
            }
            ((PunishmentProfile)pt).tempBan(sender.getName(), reason, until);
            sender.sendMessage(ChatColor.YELLOW+"1x users have been banned.");
        }
    }

}
