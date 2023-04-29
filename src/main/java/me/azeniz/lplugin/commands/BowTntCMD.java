package me.azeniz.lplugin.commands;

import me.azeniz.lplugin.util.Util;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class BowTntCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (player.isOp()) {
            ItemStack bow = new ItemStack(Material.BOW);
            bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
            ItemMeta meta = bow.getItemMeta();
            meta.getPersistentDataContainer().set(NamespacedKey.fromString("istnt"), PersistentDataType.STRING, "true");
            List<String> list = new ArrayList<>();
            list.add("Перед выстрелом нужно крикнуть allāhu akbar");
            try {
                if (Integer.parseInt(args[0]) > 30) {
                    Util.sendMessage(player, "Сила взрыва не должна быть больше &830");
                    return true;
                }
                meta.getPersistentDataContainer().set(NamespacedKey.fromString("power"), PersistentDataType.FLOAT, Float.parseFloat(args[0]));
                list.add("Сила взрыва: " + args[0]);
            } catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
                meta.getPersistentDataContainer().set(NamespacedKey.fromString("power"), PersistentDataType.FLOAT, 10.0F);
                list.add("Сила взрыва: 10");
            }

            meta.setDisplayName("Мусульманский лук");
            meta.setUnbreakable(true);
            meta.setLore(list);
            ItemStack arrow = new ItemStack(Material.ARROW, 64);
            bow.setItemMeta(meta);
            player.getInventory().addItem(bow);
            player.getInventory().addItem(arrow);
            Util.sendMessage(player, "Поаккуратней с этой штукой..");
            return true;
        } else {
            Util.errorMessage(player, "Неизвестная команда!");
            return true;
        }

    }
}
