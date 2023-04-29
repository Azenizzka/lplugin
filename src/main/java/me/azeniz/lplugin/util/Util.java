package me.azeniz.lplugin.util;

import me.azeniz.lplugin.Plugin;
import me.azeniz.lplugin.vanish.VanishPlayerManager;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Util {
    public static final String tag = "&8[&2N&8]&7 ";

    public static void sendMessage(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', tag + message));
    }

    public static void sendMessage(Player player, String message, String tag) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', tag + message));
    }

    public static void sendBroadcast(String message) {
        for (Player player:Plugin.instance.getServer().getOnlinePlayers()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', tag + message));
        }
    }

    public static void sendBroadcastWithoutTag(String message) {
        for (Player player:Plugin.instance.getServer().getOnlinePlayers()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

    public static void errorMessage(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6⚠&8]&7 " + message));
    }

    public static void sendTitle(Player player, String title, String subtitle, int stay) {
        player.sendTitle(ChatColor.translateAlternateColorCodes('&', title),
                ChatColor.translateAlternateColorCodes('&', subtitle),
                10,
                stay,
                10);
    }

    public static long getDistance(Location location1, Location location2) {
        int x1 = Math.round(location1.getBlockX());
        int x2 = Math.round(location2.getBlockX());

        int y1 = Math.round(location1.getBlockY());
        int y2 = Math.round(location2.getBlockY());

        int z1 = Math.round(location1.getBlockZ());
        int z2 = Math.round(location2.getBlockZ());

        return Math.round(Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1)+(z2-z1)*(z2-z1)));
    }

    public static void checkPosition(Player player) {
        Block block = player.getLocation().getBlock();
        Location loc = player.getLocation();
        if (block.getType() == Material.NETHER_PORTAL) {
            player.teleport(loc.add(1, 0, 1));
        }

    }

}
