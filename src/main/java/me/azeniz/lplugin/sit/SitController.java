package me.azeniz.lplugin.sit;

import me.azeniz.lplugin.Plugin;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class SitController {
    private Map<Player, ArmorStand> playerArmorStandMap = new HashMap<>();

    public boolean isSitting(Player player) {
        return playerArmorStandMap.containsKey(player);
    }

    public void sitPlayer(Player player, Location location) {
        if (player.isOnGround()) {
            ArmorStand armorStand = location.getWorld().spawn(location, ArmorStand.class);
            armorStand.setGravity(false);
            armorStand.setVisible(false);
            armorStand.addPassenger(player);
            playerArmorStandMap.put(player, armorStand);
        }
    }
/*

 public void sitPlayerRod(Player player, Location location) {
        ArmorStand armorStand = location.getWorld().spawn(location, ArmorStand.class);
        armorStand.setGravity(false);
        armorStand.setVisible(true);
        armorStand.addPassenger(player);
        playerArmorStandMap.put(player, armorStand);
        final byte[] counter = {1};
        new BukkitRunnable() {
            @Override
            public void run() {
                if (counter[0] == 1) {
                    System.out.println("++");

                    Entity pass = armorStand.getPassengers().get(0);
                    armorStand.eject();
                    armorStand.teleport(armorStand.getLocation().subtract(0.0, 0.2, 0.0));
                    counter[0] = (byte) (counter[0] - 1);
                    armorStand.addPassenger(pass);
                } else {
                    System.out.println("--");

                    Entity pass = armorStand.getPassengers().get(0);
                    armorStand.eject();
                    armorStand.teleport(armorStand.getLocation().add(0.0, 0.2, 0.0));
                    counter[0] = (byte) (counter[0] + 1);
                    armorStand.addPassenger(pass);
                }

                if (!playerArmorStandMap.containsKey(player)) {
                    cancel();
                }
            }
        }.runTaskTimer(Plugin.instance, 0L, 10L);
    }

 */

    public void delSitPlayer(Player player) {
        ArmorStand sit = playerArmorStandMap.get(player);
        sit.eject();
        sit.remove();
        player.teleport(player.getLocation().add(0.0, 1.7, 0.0));
        playerArmorStandMap.remove(player);
    }
}
