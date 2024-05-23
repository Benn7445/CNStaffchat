package me.quartz.cnstaffchat;

import me.quartz.cnstaffchat.bungee.BungeeManager;
import me.quartz.cnstaffchat.commands.*;
import me.quartz.cnstaffchat.listeners.AsyncPlayerChatListener;
import me.quartz.cnstaffchat.profile.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CNStaffchat extends JavaPlugin {

    private static CNStaffchat instance;
    private ProfileManager profileManager;
    private BungeeManager bungeeManager;
    private boolean muteChat = false;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        registerManagers();
        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
    }

    private void registerManagers() {
        profileManager = new ProfileManager();
        bungeeManager = new BungeeManager();
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
    }

    private void registerCommands() {
        getCommand("staffchat").setExecutor(new StaffChatCommand());
        getCommand("mutechat").setExecutor(new MuteChatCommand());
        getCommand("staffafk").setExecutor(new StaffAFKCommand());
        getCommand("mutestaffchat").setExecutor(new MuteStaffChatCommand());
        getCommand("stafflist").setExecutor(new StaffListCommand());
    }

    public static CNStaffchat getInstance() {
        return instance;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }

    public BungeeManager getBungeeManager() {
        return bungeeManager;
    }

    public boolean isMuteChat() {
        return muteChat;
    }

    public void toggleMuteChat() {
        this.muteChat = !this.muteChat;
    }
}
