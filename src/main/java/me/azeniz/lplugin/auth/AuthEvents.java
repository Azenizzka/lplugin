package me.azeniz.lplugin.auth;

import me.azeniz.lplugin.Plugin;
import me.azeniz.lplugin.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;


public class AuthEvents implements Listener {
    Check check = new Check();

    UnLoggedPlayersManager ulpm = Plugin.unloggedPlayersManager;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        ulpm.addUnLoggedPlayers(event.getPlayer().getUniqueId());
        check.goAuthPlayer(event.getPlayer());
    }

    @EventHandler
    public void onSendCommand(PlayerCommandPreprocessEvent event) {
        if (ulpm.checkUnLoggedPlayers(event.getPlayer().getUniqueId())) {
            String msg = event.getMessage();
            String[] args = msg.split(" ");
            Player player = event.getPlayer();
            String command = args[0];

            if (!command.equalsIgnoreCase("/reg") && !command.equalsIgnoreCase("/register") && !command.equalsIgnoreCase("/log") && !command.equalsIgnoreCase("/l") && !command.equalsIgnoreCase("/login")) {
                Util.errorMessage(player, "Вы не авторизованы!");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        ulpm.delUnLoggedPlayers(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (ulpm.checkUnLoggedPlayers(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSendChat(AsyncPlayerChatEvent event) {
        if (ulpm.checkUnLoggedPlayers(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (ulpm.checkUnLoggedPlayers(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        if (ulpm.checkUnLoggedPlayers(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onGetDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (ulpm.checkUnLoggedPlayers(player.getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onTargettingToPlayer(EntityTargetLivingEntityEvent event) {
        if (event.getTarget() instanceof Player) {
            Player player = (Player) event.getTarget();
            if (ulpm.checkUnLoggedPlayers(player.getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (ulpm.checkUnLoggedPlayers(player.getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

    /*
    @EventHandler
    public void eventName(PlayerJoinEvent event) {

    }
     */
}
