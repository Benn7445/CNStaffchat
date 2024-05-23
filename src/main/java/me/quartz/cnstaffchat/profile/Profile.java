package me.quartz.cnstaffchat.profile;

import java.util.UUID;

public class Profile {

    private final UUID uuid;
    private boolean staffChat;
    private boolean muteStaffChat;
    private boolean afk;

    public Profile(UUID uuid, boolean staffChat, boolean muteStaffChat, boolean afk) {
        this.uuid = uuid;
        this.staffChat = staffChat;
        this.muteStaffChat = muteStaffChat;
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

    public boolean isMuteStaffChat() {
        return muteStaffChat;
    }

    public void toggleMuteStaffChat() {
        this.muteStaffChat = !this.muteStaffChat;
    }

    public boolean isAfk() {
        return afk;
    }

    public void toggleAfk() {
        this.afk = !this.afk;
    }
}
