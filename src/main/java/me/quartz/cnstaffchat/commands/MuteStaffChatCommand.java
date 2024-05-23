package me.quartz.cnstaffchat.commands;

import me.quartz.cnstaffchat.CNStaffchat;
import me.quartz.cnstaffchat.profile.Profile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class MuteStaffChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission("cnstaffchat.staffchat")) {
            if(commandSender instanceof Player player) {
                Profile profile = CNStaffchat.getInstance().getProfileManager().getProfile(player.getUniqueId());
                profile.toggleMuteStaffChat();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Objects.requireNonNull(CNStaffchat.getInstance().getConfig().getString("messages.mutestaffchat-" +
                                (profile.isMuteStaffChat() ? "enabled" : "disabled")))));
            } else commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    Objects.requireNonNull(CNStaffchat.getInstance().getConfig().getString("messages.only-players"))));
        } else commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                Objects.requireNonNull(CNStaffchat.getInstance().getConfig().getString("messages.no-permissions"))));
        return true;
    }
}
