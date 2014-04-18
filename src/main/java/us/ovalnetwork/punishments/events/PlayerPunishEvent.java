package us.ovalnetwork.punishments.events;

import net.md_5.bungee.api.plugin.Event;
import us.ovalnetwork.punishments.punishments.objects.Punishment;

import java.util.UUID;

/**
 * created by codermason on 4/5/14
 */
public class PlayerPunishEvent extends Event {

    private UUID player;
    private Punishment punishment;

    public PlayerPunishEvent(UUID player, Punishment punishment) {
        this.player = player;
        this.punishment = punishment;
    }

    public UUID getPlayer() {
        return this.player;
    }

    public Punishment getPunishment() {
        return this.punishment;
    }

}
