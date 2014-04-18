package us.ovalnetwork.punishments.events;

import net.md_5.bungee.api.plugin.Event;
import us.ovalnetwork.punishments.punishments.objects.Punishment;

import java.util.UUID;

/**
 * created by codermason on 4/5/14
 */
public class PlayerAppealEvent extends Event {

    private UUID player;

    public PlayerAppealEvent(UUID player) {
        this.player = player;
    }

    public UUID getPlayer() {
        return this.player;
    }

}
