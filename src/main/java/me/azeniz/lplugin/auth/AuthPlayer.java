package me.azeniz.lplugin.auth;

import me.azeniz.lplugin.Plugin;
import me.azeniz.lplugin.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.UUID;

public class AuthPlayer {
    public void goLogin(Player player) {
        UnLoggedPlayersManager ulpm = Plugin.unloggedPlayersManager;
        Util.sendTitle(player, "&7Вам необходимо авторизоваться!", "&2/login <Пароль>", 1200);
        UUID uuid = player.getUniqueId();
        final int[] seconds = {60};

        new BukkitRunnable() {

            @Override
            public void run() {
                if (player.isOnline()) {
                    if (ulpm.checkUnLoggedPlayers(uuid)) {
                        seconds[0] -= 1;
                        if (seconds[0] == 0) {
                            player.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&7Вы не успели авторизоваться за &860 &7секунд."));
                            cancel();
                        }
                    } else {
                        cancel();
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(Plugin.instance, 0L, 20L);
    }


    public void goRegister(Player player) {
        UnLoggedPlayersManager ulpm = Plugin.unloggedPlayersManager;
        Util.sendTitle(player, "&7Вам необходимо зарегистрироваться!", "&2/register <Пароль>", 1200);
        UUID uuid = player.getUniqueId();
        final int[] seconds = {60};

        new BukkitRunnable() {

            @Override
            public void run() {
                if (player.isOnline()) {
                    if (ulpm.checkUnLoggedPlayers(uuid)) {
                        seconds[0] -= 1;
                        if (seconds[0] == 0) {
                            player.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&7Вы не успели зарегистрироваться за &860 &7секунд."));
                            cancel();
                        }
                    } else {
                        cancel();
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(Plugin.instance, 0L, 20L);
    }
}
