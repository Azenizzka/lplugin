package me.azeniz.lplugin.commands;

import me.azeniz.lplugin.Plugin;
import me.azeniz.lplugin.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CordsCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (sender instanceof Player) {
                try {
                    Player player = (Player) sender;
                    Player player2 = Bukkit.getPlayer(args[0]);
                    long distance = Util.getDistance(player.getLocation(), player2.getLocation());

                    Location playerLoc = player.getLocation();
                    int posX = Math.round(playerLoc.getBlockX());
                    int posY = Math.round(playerLoc.getBlockY());
                    int posZ = Math.round(playerLoc.getBlockZ());
                    World.Environment world = player.getWorld().getEnvironment();
                    World.Environment world2 = player2.getWorld().getEnvironment();
                    String worldString = null;

                    boolean isSameWorld;
                    if (world == world2) {
                        isSameWorld = true;
                    } else {
                        isSameWorld = false;
                    }

                    if (world == World.Environment.NORMAL) {
                        worldString = "&2Верхнем мире&7";
                    } else if (world == World.Environment.NETHER) {
                        worldString = "&cАду&7";
                    } else if (world == World.Environment.THE_END) {
                        worldString = "&5Эндер мире&7";
                    }

                    Util.sendMessage(player, "Вы успешно отправили свою позицию &8" + player2.getName(), "&8[&6➲&8]&7 ");
                    Util.sendMessage(player2, "Игрок &8" + player.getName() + "&7 поделился с вами своей позицией!", "&8[&6➲&8]&7 ");
                    Util.sendMessage(player2, "Он находится в " + worldString + " на координатах", "&8[&6➲&8]&7 ");
                    if (isSameWorld) {
                        Util.sendMessage(player2, "X: &2" + posX + "&8 | &7 Y: &2" + posY + "&8 | &7 Z: &2" + posZ + "&8 | &7Расстояние: &2" + distance, "&8[&6➲&8]&7 ");

                    } else {
                        Util.sendMessage(player2, "X: &2" + posX + "&8 | &7 Y: &2" + posY + "&8 | &7 Z: &2" + posZ, "&8[&6➲&8]&7 ");

                    }
                    return true;
                } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                    Player player = (Player) sender;
                    Util.errorMessage(player, "Укажите ник игрока, которому хотите отправить свои координаты");
                    return true;
                }
            }
        return false;
    }
}
