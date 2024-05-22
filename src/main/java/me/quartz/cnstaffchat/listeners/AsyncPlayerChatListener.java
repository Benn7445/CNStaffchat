package me.quartz.cnstaffchat.listeners;

import me.quartz.cnstaffchat.CNStaffchat;
import me.quartz.cnstaffchat.profile.Profile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler
    public void asyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        Profile profile = CNStaffchat.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if(profile.isStaffChat() || (event.getMessage().startsWith("#") && player.hasPermission("cnstaffchat.staffchat"))) {
            // Send Message
            event.setCancelled(true);
        }
    }
}
