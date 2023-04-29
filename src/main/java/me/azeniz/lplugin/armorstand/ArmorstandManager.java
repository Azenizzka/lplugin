package me.azeniz.lplugin.armorstand;

import me.azeniz.lplugin.util.Util;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.EulerAngle;

public class ArmorstandManager {
    public static void clicked(ArmorStand armorStand, Player player) {
        Util.sendMessage(player, "Введите в чат угол для головы.");
        armorStand.setHeadPose(armorStand.getHeadPose().add(1.0, 1.0, 1.0));
    }
}
