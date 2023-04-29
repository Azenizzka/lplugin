package me.azeniz.lplugin.auth;

import me.azeniz.lplugin.Plugin;
import me.azeniz.lplugin.util.Util;
import org.bukkit.entity.Player;

public class Check {

    AuthPlayer authPlayer = new AuthPlayer();

    public void goAuthPlayer (Player player) {
        Util.checkPosition(player);
        player.setAllowFlight(true);
        if (Plugin.instance.getConfig().contains(String.valueOf(player.getUniqueId()))) { // go /login
            authPlayer.goLogin(player);
        } else { // go /register
            authPlayer.goRegister(player);
        }
    }
}
