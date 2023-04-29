package me.azeniz.lplugin.vanish;

import me.azeniz.lplugin.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.*;

public class VanishEvents implements Listener {
    VanishHandler vh = new VanishHandler();

    @EventHandler
    public void onExit(PlayerQuitEvent event) {
        vh.hideVanishPlayers();
        if (vh.isVanish(event.getPlayer())) {
            vh.delVanishPlayer(event.getPlayer());
        }
    }


    @EventHandler
    public void sendChat(AsyncPlayerChatEvent event) {
        if (vh.isVanish(event.getPlayer())) {
            Util.errorMessage(event.getPlayer(), "В ванише нельзя писать в чат!");
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onGetDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (vh.isVanish(player.getPlayer())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPickUpItem(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (vh.isVanish(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onTargettingToPlayer(EntityTargetLivingEntityEvent event) {
        if (event.getTarget() instanceof Player) {
            Player player = (Player) event.getTarget();
            if (vh.isVanish(player)) {
                event.setCancelled(true);
            }
        }
    }
}
