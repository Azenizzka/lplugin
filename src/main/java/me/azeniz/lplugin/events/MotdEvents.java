package me.azeniz.lplugin.events;

import me.azeniz.lplugin.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.util.CachedServerIcon;

import java.io.File;

public class MotdEvents implements Listener {
    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        File iconPath = new File("icon.png");
        CachedServerIcon icon;
        try {
            icon = Plugin.instance.getServer().loadServerIcon(iconPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        event.setServerIcon(icon);
        event.setMotd(ChatColor.translateAlternateColorCodes('&', "&2&lNMINE\n&r&7Начало бета-теста: &831.03.2023"));
    }

}
