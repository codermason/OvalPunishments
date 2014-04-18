package us.ovalnetwork.punishments.listeners;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import us.ovalnetwork.api.profiles.Profile;
import us.ovalnetwork.api.utils.UUIDLibrary;
import us.ovalnetwork.punishments.OvalPunishments;
import us.ovalnetwork.punishments.profiles.PunishmentProfile;
import us.ovalnetwork.punishments.punishments.Mute;
import us.ovalnetwork.punishments.punishments.temps.TempMute;

/**
 * created by codermason on 4/3/14
 */
public class ChatEvent implements Listener {

    private OvalPunishments plugin;

    public ChatEvent(OvalPunishments plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(net.md_5.bungee.api.event.ChatEvent e) {
        Profile p = plugin.getManager().getProfile(UUIDLibrary.getUUID(e.getSender().toString()));

        PunishmentProfile profile = (PunishmentProfile) p;
        if(profile.isMuted()) {
            Mute mute = profile.getMute();
            profile.getPlayer().sendMessages(mute.getMessage().split("\n"));
            e.setCancelled(true);
        }else if(profile.isTempMuted()) {
            TempMute tempmute = profile.getTempMute();
            if(tempmute.isOver()) {
                profile.unmute();
                return;
            }
            profile.getPlayer().sendMessages(tempmute.getMessage().split("\n"));
            e.setCancelled(true);
        }
    }

}
