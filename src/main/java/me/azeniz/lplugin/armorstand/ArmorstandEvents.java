package me.azeniz.lplugin.armorstand;

import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ArmorstandEvents implements Listener {
    @EventHandler
    public void onClickArmorstand(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof ArmorStand) {
            ArmorstandManager.clicked((ArmorStand) event.getRightClicked(), event.getPlayer());
        }
    }
}
