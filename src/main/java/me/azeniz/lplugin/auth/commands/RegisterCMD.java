package me.azeniz.lplugin.auth.commands;

import me.azeniz.lplugin.Plugin;
import me.azeniz.lplugin.auth.UnLoggedPlayersManager;
import me.azeniz.lplugin.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class RegisterCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            UnLoggedPlayersManager ulpm = Plugin.unloggedPlayersManager;
            Player player = (Player) sender;
            UUID uuid = player.getUniqueId();

            if (ulpm.checkUnLoggedPlayers(uuid)) {
                if (args.length == 1) {
                    String password = args[0];
                    Plugin.instance.getConfig().set(String.valueOf(uuid), password);
                    Plugin.instance.getConfig().set(player.getName(), String.valueOf(uuid));
                    Plugin.instance.saveConfig();

                    ulpm.delUnLoggedPlayers(uuid);
                    player.setAllowFlight(false);
                    Util.sendTitle(player, "&7Успешная регистрация!", "&2Приятной игры&c❤", 60);
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
