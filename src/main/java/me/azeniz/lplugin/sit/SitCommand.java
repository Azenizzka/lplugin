package me.azeniz.lplugin.sit;

import me.azeniz.lplugin.Plugin;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SitCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        SitController sit = Plugin.sitController;
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (sit.isSitting(player)) {
                sit.delSitPlayer(player);
            } else {
                Location loc = player.getLocation().subtract(0.0, 1.7, 0.0);
                sit.sitPlayer(player, loc);
            }
        }
        return false;
    }
}
