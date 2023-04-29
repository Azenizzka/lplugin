package me.azeniz.lplugin.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SpawnEvokerCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Location loc = player.getLocation();
        for (int i = 0; i < 100; i++) {
            player.getWorld().spawnEntity(loc, EntityType.EVOKER);
        }
        return true;
    }
}
