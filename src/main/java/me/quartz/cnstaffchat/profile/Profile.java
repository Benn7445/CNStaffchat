package me.quartz.cnstaffchat.profile;

import java.util.UUID;

public class Profile {

    private final UUID uuid;
    private boolean staffChat;
    private boolean afk;

    public Profile(UUID uuid, boolean staffChat, boolean afk) {
        this.uuid = uuid;
        this.staffChat = staffChat;
        this.afk = afk;
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean isStaffChat() {
        return staffChat;
    }

    public void toggleStaffChat() {
        this.staffChat = !this.staffChat;
    }

    public boolean isAfk() {
        return afk;
    }

    public void setAfk(boolean afk) {
        this.afk = afk;
    }
}
