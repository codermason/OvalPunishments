package us.ovalnetwork.punishments.commands.appeals;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import us.ovalnetwork.api.utils.UUIDLibrary;
import us.ovalnetwork.punishments.OvalPunishments;
import us.ovalnetwork.punishments.profiles.PunishmentProfile;

/**
 * created by codermason on 4/5/14
 */
public class UnbanCommand extends Command {

    private OvalPunishments plugin;

    public UnbanCommand(OvalPunishments plugin) {
        super("g:unban", "oval.punishments.global.commands.unban", new String[]{});
        this.plugin = plugin;
    }

    public void execute(CommandSender sender, String[] args) {
        if(args.length == 1) {
            String target = args[0];
            PunishmentProfile pt = (PunishmentProfile) plugin.getManager().getProfile(UUIDLibrary.getUUID(target));
            if(!pt.isTempBanned() && !pt.isBanned()) {
                sender.sendMessage(ChatColor.RED+target+" is not banned.");
                return;
            }
            if(pt.unban()) {
                sender.sendMessage(ChatColor.YELLOW+"1x users unbanned.");
            }else{
                sender.sendMessage(ChatColor.RED+"Error unbanning 1x users.");
            }
        }else{
            sender.sendMessage(ChatColor.YELLOW+"/g:unban <player>");
        }
    }
}
