package me.quartz.cnstaffchat.listeners;

import me.quartz.cnstaffchat.CNStaffchat;
import me.quartz.cnstaffchat.profile.Profile;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler
    public void asyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        Profile profile = CNStaffchat.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if(profile.isStaffChat() || (event.getMessage().startsWith("#") && player.hasPermission("cnstaffchat.staffchat"))) {
            LuckPerms api = LuckPermsProvider.get();
            String prefix = api.getGroupManager().getGroup(api.getUserManager().getUser(player.getUniqueId()).getPrimaryGroup()).getCachedData().getMetaData().getPrefix();

            String message = ChatColor.translateAlternateColorCodes('&',
                    Objects.requireNonNull(CNStaffchat.getInstance().getConfig().getString("messages.staff-message"))
                            .replace("%prefix%", prefix != null ? prefix : "")
                            .replace("%player%", player.getName())
                            .replace("%message%", event.getMessage().substring(event.getMessage().startsWith("#") ? 1 : 0)));
            CNStaffchat.getInstance().getBungeeManager().sendMessage("StaffChat", message);
            event.setCancelled(true);
        } else if(CNStaffchat.getInstance().isMuteChat()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    Objects.requireNonNull(CNStaffchat.getInstance().getConfig().getString("messages.chat-muted"))));
            event.setCancelled(true);
        }
    }
}
