package me.quartz.cnstaffchat.commands;

import me.quartz.cnstaffchat.CNStaffchat;
import me.quartz.cnstaffchat.profile.Profile;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class StaffListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission("cnstaffchat.stafflist")) {
            if(commandSender instanceof Player player) {
                player.sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD + "Online Staff Members" + ChatColor.GRAY + " (" + Bukkit.getOnlinePlayers().stream().filter(player1 -> player1.hasPermission("cnstaffchat.staffchat")).count() + "):");
                player.sendMessage(" ");
                for(String rank : CNStaffchat.getInstance().getConfig().getConfigurationSection("ranks").getKeys(false)) {
                    LuckPerms api = LuckPermsProvider.get();
                    Group group = api.getGroupManager().getGroup(rank);
                    if(group != null) {
                        player.sendMessage("    " + ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(CNStaffchat.getInstance().getConfig().getString("ranks." + rank))) + ChatColor.GRAY + ":");
                        Bukkit.getOnlinePlayers().forEach(player1 -> {
                            Profile profile = CNStaffchat.getInstance().getProfileManager().getProfile(player1.getUniqueId());
                            if(api.getGroupManager().getGroup(api.getUserManager().getUser(player.getUniqueId()).getPrimaryGroup()).getName().equals(group.getName())) {
                                player.sendMessage("    " + ChatColor.GRAY + "- " + player1.getName() + " - " + (profile.isAfk() ? "Is AFK" : "Is Available"));
                            }
                        });
                        player.sendMessage(" ");
                    }
                }
            }
        }
        return true;
    }
}
