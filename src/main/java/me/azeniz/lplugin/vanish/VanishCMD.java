package me.azeniz.lplugin.vanish;

import me.azeniz.lplugin.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp()) {
                VanishHandler vh = new VanishHandler();
                if (vh.isVanish(player)) {
                    vh.delVanishPlayer(player);
                } else {
                    vh.addVanishPlayer(player);
                }
                return true;
            } else {
                Util.errorMessage(player, "Неизвестная команда!");
                return true;
            }
        }
        return true;
    }
}
