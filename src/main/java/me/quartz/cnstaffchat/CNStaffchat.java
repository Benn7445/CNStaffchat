package me.quartz.cnstaffchat;

import me.quartz.cnstaffchat.commands.StaffChatCommand;
import me.quartz.cnstaffchat.listeners.AsyncPlayerChatListener;
import me.quartz.cnstaffchat.profile.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CNStaffchat extends JavaPlugin {

    private static CNStaffchat instance;
    private ProfileManager profileManager;

    @Override
    public void onEnable() {
        instance = this;
        registerManagers();
        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
    }

    private void registerManagers() {
        profileManager = new ProfileManager();
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
    }

    private void registerCommands() {
        getCommand("staffchat").setExecutor(new StaffChatCommand());
    }

    public static CNStaffchat getInstance() {
        return instance;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }
}
