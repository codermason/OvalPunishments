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
public class UnmuteCommand extends Command {

    private OvalPunishments plugin;

    public UnmuteCommand(OvalPunishments plugin) {
        super("g:unmute", "oval.punishments.global.commands.unmute", new String[]{});
        this.plugin = plugin;
    }

    public void execute(CommandSender sender, String[] args) {
        if(args.length == 1) {
            String target = args[0];
            PunishmentProfile pt = (PunishmentProfile) plugin.getManager().getProfile(UUIDLibrary.getUUID(target));
            if(!pt.isMuted() && !pt.isTempMuted()) {
                sender.sendMessage(ChatColor.RED+target+" is not muted.");
                return;
            }
            if(pt.unmute()) {
                sender.sendMessage(ChatColor.YELLOW+"1x users unmuted.");
            }else{
                sender.sendMessage(ChatColor.RED+"Error unmuting 1x players.");
            }
        }else{
            sender.sendMessage(ChatColor.YELLOW+"/g:unmute <player>");
        }
    }
}