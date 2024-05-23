package me.quartz.cnstaffchat.commands;

import me.quartz.cnstaffchat.CNStaffchat;
import me.quartz.cnstaffchat.profile.Profile;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class StaffAFKCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission("cnstaffchat.staffafk")) {
            if(commandSender instanceof Player player) {
                LuckPerms api = LuckPermsProvider.get();
                String prefix = api.getGroupManager().getGroup(api.getUserManager().getUser(player.getUniqueId()).getPrimaryGroup()).getCachedData().getMetaData().getPrefix();

                Profile profile = CNStaffchat.getInstance().getProfileManager().getProfile(player.getUniqueId());
                profile.toggleAfk();
                String message = ChatColor.translateAlternateColorCodes('&',
                        Objects.requireNonNull(CNStaffchat.getInstance().getConfig().getString("messages.staffafk-" +
                                (profile.isAfk() ? "enabled" : "disabled"))).replace("%player%", (prefix != null ? prefix : "") + player.getName()));
                CNStaffchat.getInstance().getBungeeManager().sendMessage("StaffAFK", message);
            } else commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    Objects.requireNonNull(CNStaffchat.getInstance().getConfig().getString("messages.only-players"))));
        } else commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                Objects.requireNonNull(CNStaffchat.getInstance().getConfig().getString("messages.no-permissions"))));
        return true;
    }
}
