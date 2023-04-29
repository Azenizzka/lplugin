package me.azeniz.lplugin.vanish;

import me.azeniz.lplugin.Plugin;
import me.azeniz.lplugin.util.Util;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class VanishHandler {
    VanishPlayerManager vpm = Plugin.vanishPlayerManager;
    public void addVanishPlayer(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
        Util.sendBroadcastWithoutTag("&8[&c-&8]&7 " + player.getDisplayName());
        Util.sendMessage(player, "Теперь вы &2невидимы", "&8[&5⚐&8]&7 ");
        vpm.addVanishPlayer(player);
        hideVanishPlayers();
        player.setInvisible(true);
        player.setSilent(true);
        player.setAllowFlight(true);
    }

    public void delVanishPlayer(Player player) {
        Util.sendBroadcastWithoutTag("&8[&2+&8]&7 " + player.getDisplayName());
        player.setGameMode(GameMode.SURVIVAL);
        Util.sendMessage(player, "Теперь вы &cвидимы", "&8[&5⚐&8]&7 ");
        player.teleport(vpm.getVanishPlayerLocation(player));
        vpm.delVanishPlayer(player);
        hideVanishPlayers();
        player.setInvisible(false);
        player.setSilent(false);
        player.setAllowFlight(false);
        player.setFlying(false);
    }

    public void hideVanishPlayers() {
        for (Player p : Plugin.instance.getServer().getOnlinePlayers()) { // [1,2,3]
            for (Player v : Plugin.instance.getServer().getOnlinePlayers()) { // [1,2,3]
                if (p != v) {
                    if (vpm.getMap().containsKey(v)) {
                        p.hidePlayer(Plugin.instance, v);
                    } else {
                        p.showPlayer(Plugin.instance, v);
                    }
                }
            }
        }
    }

    public boolean isVanish(Player player) {
        return vpm.isVanish(player);
    }
}
