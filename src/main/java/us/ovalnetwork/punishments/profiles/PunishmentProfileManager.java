package us.ovalnetwork.punishments.profiles;

import us.ovalnetwork.api.OvalAPI;
import us.ovalnetwork.api.profiles.Profile;
import us.ovalnetwork.api.profiles.ProfileManager;

import java.util.UUID;

/**
 * created by codermason on 4/3/14
 */
public class PunishmentProfileManager extends ProfileManager {

    public PunishmentProfileManager(OvalAPI api) {
        super(api);
    }

    public Profile registerProfile(UUID uuid) {
        PunishmentProfile profile = new PunishmentProfile(uuid);
        super.getProfiles().add(profile);
        return profile;
    }

    public Profile getProfile(UUID uuid) {
        for(Profile profile : super.getProfiles())
            if(profile.getUUID().equals(uuid) && profile instanceof PunishmentProfile)
                return profile;
        return registerProfile(uuid);
    }
}
