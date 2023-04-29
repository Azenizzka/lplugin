package me.azeniz.lplugin.commands;

import me.azeniz.lplugin.util.Util;
import me.azeniz.lplugin.vanish.VanishHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PushMessageCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            try {
                VanishHandler vh = new VanishHandler();
                Player player = (Player) sender;
                Player player2 = Bukkit.getPlayer(args[0]);
                if (!vh.isVanish(player2)) {
                    String message = " ";
                    for (int i = 1; i < args.length; i++) {
                        message = message + " " + args[i];
                    }
                    Util.sendMessage(player, "Вы &2➡&7 " + player2.getName() + ":&6" + message, "&8[&9✉&8]&7 ");
                    Util.sendMessage(player2, player.getName() + " &2➡&7 Вам:&6" + message, "&8[&9✉&8]&7 ");
                    return true;
                } else {
                    Util.errorMessage(player, "Укажите ник игрока, которому хотите написать сообщение");
                }
            } catch (NullPointerException e) {
                Player player = (Player) sender;
                Util.errorMessage(player, "Укажите ник игрока, которому хотите написать сообщение");
            }
        }
        return true;
    }
}
