package me.azeniz.lplugin.vanish;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class VanishPlayerManager {
    private Map<Player, Location> vanishPlayersLocation = new HashMap<>();

    public void addVanishPlayer(Player player) {
        vanishPlayersLocation.put(player, player.getLocation());
    }

    public Map<Player, Location> getMap() {
        return vanishPlayersLocation;
    }

    public void delVanishPlayer(Player player) {
        vanishPlayersLocation.remove(player);
    }

    public Location getVanishPlayerLocation(Player player) {
        return vanishPlayersLocation.get(player);
    }

    public boolean isVanish(Player player) {
        return vanishPlayersLocation.containsKey(player);
    }
}
