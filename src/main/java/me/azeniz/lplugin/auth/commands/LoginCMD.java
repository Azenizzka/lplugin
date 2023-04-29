package me.azeniz.lplugin.auth.commands;

import me.azeniz.lplugin.Plugin;
import me.azeniz.lplugin.auth.UnLoggedPlayersManager;
import me.azeniz.lplugin.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class LoginCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            UnLoggedPlayersManager ulpm = Plugin.unloggedPlayersManager;
            Player player = (Player) sender;
            UUID uuid = player.getUniqueId();

            if (ulpm.checkUnLoggedPlayers(uuid)) {
                if (args.length == 1) {
                    String text = args[0];
                    String password = Plugin.instance.getConfig().getString(String.valueOf(uuid));

                    if (password.equals(text)) {
                        ulpm.delUnLoggedPlayers(uuid);
                        player.setAllowFlight(false);
                        Util.sendTitle(player, "&7Успешная авторизация!", "&2Приятной игры&c❤", 60);
                    } else {
                        player.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&cНеверный &7пароль!"));
                    }
                } else {
                    Util.errorMessage((Player)sender, "Слишком много аргументов!");
                }
            } else {
                Util.errorMessage((Player)sender, "Вы уже авторизованы");
            }
        }
        return true;
    }
}
