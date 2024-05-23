package me.quartz.cnstaffchat.bungee;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.quartz.cnstaffchat.CNStaffchat;
import me.quartz.cnstaffchat.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BungeeManager implements PluginMessageListener {

    public BungeeManager() {
        CNStaffchat.getInstance().getServer().getMessenger().registerIncomingPluginChannel(CNStaffchat.getInstance(), "BungeeCord", this);
        CNStaffchat.getInstance().getServer().getMessenger().registerOutgoingPluginChannel(CNStaffchat.getInstance(), "BungeeCord");
    }

    public void sendMessage(String channel, String message) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            Profile profile = CNStaffchat.getInstance().getProfileManager().getProfile(player.getUniqueId());
            if(player.hasPermission("cnstaffchat." + channel.toLowerCase()) && !profile.isMuteStaffChat()) player.sendMessage(message);
        });

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Forward");
        out.writeUTF("ALL");
        out.writeUTF(channel);

        ByteArrayOutputStream msgbytes = new ByteArrayOutputStream();
        DataOutputStream msgout = new DataOutputStream(msgbytes);
        try {
            msgout.writeUTF(message);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        out.writeShort(msgbytes.toByteArray().length);
        out.write(msgbytes.toByteArray());

        CNStaffchat.getInstance().getServer().sendPluginMessage(CNStaffchat.getInstance(), "BungeeCord", out.toByteArray());
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("StaffChat")) {
            String scMessage = in.readUTF();
            Bukkit.getOnlinePlayers().forEach(players -> {
                Profile profile = CNStaffchat.getInstance().getProfileManager().getProfile(player.getUniqueId());
                if(players.hasPermission("cnstaffchat.staffchat") && !profile.isMuteStaffChat()) players.sendMessage(scMessage.substring(2));
            });
        } else if (subchannel.equals("StaffAFK")) {
            String scMessage = in.readUTF();
            Bukkit.getOnlinePlayers().forEach(players -> {
                Profile profile = CNStaffchat.getInstance().getProfileManager().getProfile(player.getUniqueId());
                if(players.hasPermission("cnstaffchat.staffafk") && !profile.isMuteStaffChat()) players.sendMessage(scMessage.substring(2));
            });
        }
    }
}