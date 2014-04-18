package us.ovalnetwork.punishments.listeners;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import us.ovalnetwork.api.profiles.Profile;
import us.ovalnetwork.api.utils.UUIDLibrary;
import us.ovalnetwork.punishments.OvalPunishments;
import us.ovalnetwork.punishments.profiles.PunishmentProfile;
import us.ovalnetwork.punishments.punishments.Ban;
import us.ovalnetwork.punishments.punishments.temps.TempBan;

import java.util.UUID;

/**
 * created by codermason on 3/31/14
 */
public class LoginEvent implements Listener {

    private OvalPunishments plugin;

    public LoginEvent(OvalPunishments plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onConnect(net.md_5.bungee.api.event.LoginEvent e) {
        UUID uuid = UUIDLibrary.getUUID(e.getConnection().getName());
        Profile p = plugin.getManager().getProfile(uuid);
        PunishmentProfile profile = (PunishmentProfile) p;

        if(profile.isBanned()) {
            Ban recent = profile.getBan();
            e.setCancelled(true);
            e.setCancelReason(recent.getMessage());
        }else if(profile.isTempBanned()) {
            TempBan recent = profile.getTempBan();
            if(recent.isOver()) {
                profile.unban();
                return;
            }
            e.setCancelled(true);
            e.setCancelReason(recent.getMessage());
        }
    }

}
