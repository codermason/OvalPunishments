package us.ovalnetwork.punishments.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import us.ovalnetwork.api.profiles.Profile;
import us.ovalnetwork.api.utils.UUIDLibrary;
import us.ovalnetwork.punishments.OvalPunishments;
import us.ovalnetwork.punishments.profiles.PunishmentProfile;

/**
 * created by codermason on 3/31/14
 */
public class MuteCommand extends Command {

    private OvalPunishments plugin;

    public MuteCommand(OvalPunishments plugin) {
        super("g:mute", "oval.punishments.global.commands.mute", new String[]{});
        this.plugin = plugin;
    }

    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW+"/g:mute <player> [reason]");
        }else{
            String target = args[0];
            Profile pt = plugin.getManager().getProfile(UUIDLibrary.getUUID(target));
            if(pt == null) {
                sender.sendMessage(ChatColor.RED+target+" not found.");
                return;
            }else if(((PunishmentProfile)pt).isMuted() || ((PunishmentProfile)pt).isTempMuted()) {
                sender.sendMessage(ChatColor.RED+target+" is already muted.");
                return;
            }
            String reason = "";
            if(args.length > 1) {
                for(int i=1;i<args.length;i++)
                    reason += args[i] + " ";
            }
            ((PunishmentProfile)pt).mute(sender.getName(), reason);
            sender.sendMessage(ChatColor.YELLOW+"1x users have been muted.");
        }
    }

}
