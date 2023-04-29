package me.azeniz.lplugin.events;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;


public class TabListEvents implements Listener {
    @EventHandler
    static void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String header = "&2&lNMine";
        String footer = "";
        String nickname = event.getPlayer().getName();
        World.Environment world = player.getWorld().getEnvironment();

        player.setPlayerListHeaderFooter(ChatColor.translateAlternateColorCodes('&', header), ChatColor.translateAlternateColorCodes('&', footer));

        if (world == World.Environment.NORMAL) {
            char unicode = '⛏';
            player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&8[&2" + unicode + "&8]&7 " + nickname));
        } else if (world == World.Environment.NETHER) {
            String unicode = "\uD83D\uDD25";
            player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&8[&c" + unicode + "&8]&7 " + nickname));
        } else if (world == World.Environment.THE_END) {
            char unicode = '☄';
            player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&8[&5" + unicode + "&8]&7 " + nickname));
        }
    }

    @EventHandler
    static void onChangeWorld(PlayerChangedWorldEvent event) {
        String nickname = event.getPlayer().getName();
        Player player = event.getPlayer();
        World.Environment world = player.getWorld().getEnvironment();

            if (world == World.Environment.NORMAL) {
                char unicode = '⛏';
                player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&8[&2" + unicode + "&8]&7 " + nickname));
            } else if (world == World.Environment.NETHER) {
                String unicode = "\uD83D\uDD25";
                player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&8[&c" + unicode + "&8]&7 " + nickname));
            } else if (world == World.Environment.THE_END) {
                char unicode = '☄';
                player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&8[&5" + unicode + "&8]&7 " + nickname));
            }
    }
}
