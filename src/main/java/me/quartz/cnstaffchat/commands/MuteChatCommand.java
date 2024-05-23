package me.quartz.cnstaffchat.commands;

import me.quartz.cnstaffchat.CNStaffchat;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class MuteChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission("cnstaffchat.mutechat")) {
            if(commandSender instanceof Player player) {
                LuckPerms api = LuckPermsProvider.get();
                String prefix = api.getGroupManager().getGroup(api.getUserManager().getUser(player.getUniqueId()).getPrimaryGroup()).getCachedData().getMetaData().getPrefix();

                CNStaffchat.getInstance().toggleMuteChat();
                if(CNStaffchat.getInstance().isMuteChat()) Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                        Objects.requireNonNull(CNStaffchat.getInstance().getConfig().getString("messages.muted-chat"))
                                .replace("%player%", (prefix != null ? prefix : "") + player.getName())));
                else Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                        Objects.requireNonNull(CNStaffchat.getInstance().getConfig().getString("messages.unmuted-chat"))
                                .replace("%player%", (prefix != null ? prefix : "") + player.getName())));
            }
        }
        return true;
    }
}
