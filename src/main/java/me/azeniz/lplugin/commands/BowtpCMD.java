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

import java.util.Collections;

public class BowtpCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(player.isOp()) {
            ItemStack bow = new ItemStack(Material.BOW);
            bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
            ItemMeta meta = bow.getItemMeta();
            meta.getPersistentDataContainer().set(NamespacedKey.fromString("istp"), PersistentDataType.STRING, "true");
            meta.setDisplayName("Магический лук");
            meta.setUnbreakable(true);
            meta.setLore(Collections.singletonList("Кажется, он был создан цыганскими колдунами.."));
            ItemStack arrow = new ItemStack(Material.ARROW, 64);
            bow.setItemMeta(meta);
            player.getInventory().addItem(bow);
            player.getInventory().addItem(arrow);
            Util.sendMessage(player, "Теперь у тебя есть магический лук!");

            return true;
        } else {
            Util.errorMessage(player, "Неизвестная команда!");
            return true;
        }

    }
}
