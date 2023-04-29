package me.azeniz.lplugin.events;

import me.azeniz.lplugin.Plugin;
import me.azeniz.lplugin.auth.Check;
import me.azeniz.lplugin.util.Util;
import me.azeniz.lplugin.vanish.VanishEvents;
import me.azeniz.lplugin.vanish.VanishHandler;
import me.azeniz.lplugin.vanish.VanishPlayerManager;
import org.bukkit.*;
import org.bukkit.advancement.AdvancementDisplayType;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;
import java.util.Objects;
import java.util.Random;



public class Events implements Listener {

    VanishHandler vh = new VanishHandler();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&8[&2+&8]&7 " + event.getPlayer().getName()));
    }

    @EventHandler
    public void onExit(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', "&8[&c-&8]&7 " + event.getPlayer().getName()));
    }

    @EventHandler
    public void chatFormatting(AsyncPlayerChatEvent event) {
        String world = event.getPlayer().getWorld().getName();
        String message = event.getMessage();
        if (message.contains("&")) {
            Util.sendMessage(event.getPlayer(), "В чате запрещено использовать символ &c&");
            event.setCancelled(true);
        } else {
            if (world.equalsIgnoreCase("world")) {
                String unicode = "⛏";
                event.setFormat(ChatColor.translateAlternateColorCodes('&', "&8[&2" + unicode + "&8]&7 " + event.getPlayer().getName() + " &2>&7 " + message));
            } else if (world.equalsIgnoreCase("world_nether")) {
                String unicode = "\uD83D\uDD25";
                event.setFormat(ChatColor.translateAlternateColorCodes('&', "&8[&c" + unicode + "&8]&7 " + event.getPlayer().getName() + " &2>&7 " + message));
            } else if (world.equalsIgnoreCase("world_the_end")) {
                char unicode = '☄';
                event.setFormat(ChatColor.translateAlternateColorCodes('&', "&8[&5" + unicode + "&8]&7 " + event.getPlayer().getName() + " &2>&7 " + message));
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(ChatColor.translateAlternateColorCodes('&', "&8[&c☠&8]&7 " + event.getEntity().getName() + " помер"));
        System.out.println("==============");
        System.out.println(event.getDeathMessage());
        System.out.println("==============");
    }

    @EventHandler
    public void onSendUnknownCommand(PlayerCommandPreprocessEvent event) {
        String msg = event.getMessage();
        String[] args = msg.split(" ");
        if (Bukkit.getServer().getHelpMap().getHelpTopic(args[0]) == null) {
            Player player = event.getPlayer();
            Util.errorMessage(player, "Неизвестная команда!");
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onShootBow(ProjectileHitEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();
            ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();

            if (event.getEntity() instanceof Arrow) {
                Location location = event.getEntity().getLocation();

                try {
                    if (meta.getPersistentDataContainer().get(NamespacedKey.fromString("istp"), PersistentDataType.STRING).equalsIgnoreCase("true")) {
                        if (event.getEntity() instanceof Arrow) {
                            player.teleport(location);
                            Util.sendMessage(player, "Вжух! Магия, не иначе..");
                            event.getEntity().remove();
                            event.setCancelled(true);
                        }
                    }
                } catch (NullPointerException e) {}

                try {
                    if (meta.getPersistentDataContainer().get(NamespacedKey.fromString("istnt"), PersistentDataType.STRING).equalsIgnoreCase("true")) {
                        if (event.getEntity() instanceof Arrow) {
                            float power = meta.getPersistentDataContainer().get(NamespacedKey.fromString("power"), PersistentDataType.FLOAT);
                            World world = event.getEntity().getWorld();
                            world.createExplosion(location, power, true, true);
                            world.strikeLightningEffect(location);
                            event.getEntity().remove();
                            event.setCancelled(true);
                        }
                    }
                } catch (NullPointerException e) {}

            }
        }
    }

    @EventHandler
    public void onGetAdvancement(PlayerAdvancementDoneEvent event) {
        try {
            if (Objects.requireNonNull(event.getAdvancement().getDisplay()).getType().equals(AdvancementDisplayType.CHALLENGE)) {
                Util.sendBroadcast(event.getPlayer().getName() + " завершил испытание &5" + event.getAdvancement().getDisplay().getTitle());
                Player player = event.getPlayer();
                Util.sendMessage(player, "За выполнение испытания вам начисляется&8 30&7 уровней!");
                player.giveExpLevels(30);
            }
        } catch (NullPointerException e) {
        }
    }

    @EventHandler
    public void onDeathSendCoords(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        Location playerLoc = player.getLocation();
        int posX = Math.round(playerLoc.getBlockX());
        int posY = Math.round(playerLoc.getBlockY());
        int posZ = Math.round(playerLoc.getBlockZ());
        String world = player.getWorld().getName();

        if (world.equalsIgnoreCase("world")) {
            world = "&2Верхний мир&7";
        } else if (world.equalsIgnoreCase("world_nether")) {
            world = "&cАд&7";
        } else if (world.equalsIgnoreCase("world_the_end")) {
            world = "&5Эндер мир&7";
        }
        Util.sendMessage(player, "Место гибели: " + world, "&8[&6➲&8]&7 ");
        Util.sendMessage(player, "X: &2" + posX + "&8 | &7 Y: &2" + posY + "&8 | &7 Z: &2" + posZ, "&8[&6➲&8]&7 ");
    }

    Random random = new Random();

    @EventHandler
    public void changeChanceDropTotem(EntityDeathEvent event) {
        Collection<ItemStack> itemStackCollection = event.getDrops();
        ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
        int randomNumber;
        if (itemStackCollection.contains(totem)) {
            if (event.getEntity() instanceof Evoker) {
                randomNumber = random.nextInt(0, 50); // От 0 до 44, т.е 45 чисел == шанс 2%
                if (randomNumber != 7) {
                    event.getDrops().clear();
                }
            }
        }
    }

}


