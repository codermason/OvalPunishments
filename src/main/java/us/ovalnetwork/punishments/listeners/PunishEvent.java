package us.ovalnetwork.punishments.listeners;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import us.ovalnetwork.api.profiles.Profile;
import us.ovalnetwork.punishments.OvalPunishments;
import us.ovalnetwork.punishments.events.PlayerPunishEvent;

import java.util.concurrent.TimeUnit;

/**
 * created by codermason on 4/5/14
 */
public class PunishEvent implements Listener {

    private OvalPunishments plugin;

    public PunishEvent(OvalPunishments plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPunish(PlayerPunishEvent e) {
        final Profile profile = plugin.getManager().getProfile(e.getPlayer());
        ProxyServer.getInstance().getScheduler().schedule(plugin, new Runnable() {
            @Override
            public void run() {
                profile.refresh();
            }
        }, 1, TimeUnit.SECONDS);
    }

}
