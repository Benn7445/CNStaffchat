package me.quartz.cnstaffchat.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProfileManager {

    private final List<Profile> profiles;

    public ProfileManager() {
        this.profiles = new ArrayList<>();
    }

    public Profile getProfile(UUID uuid) {
        Profile profile = profiles.stream().filter(profile1 -> profile1.getUuid().toString().equals(uuid.toString())).findFirst().orElse(null);
        if(profile == null) {
            profile = new Profile(uuid, false, false);
            profiles.add(profile);
        }
        return profile;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }
}
