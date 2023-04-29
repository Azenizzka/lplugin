package me.azeniz.lplugin.sit;

import me.azeniz.lplugin.Plugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.StructureSearchResult;
import org.spigotmc.event.entity.EntityDismountEvent;

public class SitEvents implements Listener {
    @EventHandler
    public void onClickBlock(PlayerInteractEvent event) {
        /*
        OLD VERSION^

                if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getPlayer().getItemInUse() == null) {
                Material blockMaterial = event.getClickedBlock().getType();
                String blockMaterialString = blockMaterial.toString();
                if (blockMaterialString.endsWith("STAIRS") || blockMaterialString.endsWith("SLAB")) {
                    SitController sit = Plugin.sitController;
                    if (!sit.isSitting(event.getPlayer())) {
                        Location loc = event.getClickedBlock().getLocation().subtract(0.0, 1.2, 0.0);
                        sit.sitPlayer(event.getPlayer(), loc.add(0.5, 0.0, 0.5));
                    }
                }
            }
        }

         */
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Material blockMaterial = event.getClickedBlock().getType();
            String blockMaterialString = blockMaterial.toString();
            if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) {
                if (blockMaterialString.endsWith("STAIRS")) {
                    SitController sit = Plugin.sitController;
                    if (!sit.isSitting(event.getPlayer())) {
                        Location loc = event.getClickedBlock().getLocation();
                        if (loc.add(0.0, 1.0, 0.0).getBlock().isEmpty()) {
                            if (loc.add(0.0, 1.0, 0.0).getBlock().isEmpty()) {
                                loc.subtract(0.0, 1.2, 0.0);
                                Plugin.instance.getServer().getWorld("world");
                                sit.sitPlayer(event.getPlayer(), loc.add(0.5, 0.0, 0.5));
                            }
                        }
                    }
                }

                if (blockMaterialString.endsWith("SLAB")) {
                    SitController sit = Plugin.sitController;
                    if (!sit.isSitting(event.getPlayer())) {
                        Location loc = event.getClickedBlock().getLocation();
                        if (loc.add(0.0, 1.0, 0.0).getBlock().isEmpty()) {
                            if (loc.add(0.0, 1.0, 0.0).getBlock().isEmpty()) {
                                loc = event.getClickedBlock().getLocation().subtract(0.0, 1.2, 0.0);
                                sit.sitPlayer(event.getPlayer(), loc.add(0.5, 0.0, 0.5));
                            }
                        }
                    }
                }

                if (blockMaterialString.endsWith("CARPET")) {
                    SitController sit = Plugin.sitController;
                    if (!sit.isSitting(event.getPlayer())) {
                        Location loc = event.getClickedBlock().getLocation();
                        if (loc.add(0.0, 1.0, 0.0).getBlock().isEmpty()) {
                            if (loc.add(0.0, 1.0, 0.0).getBlock().isEmpty()) {
                                loc = event.getClickedBlock().getLocation().subtract(0.0, 1.7, 0.0);
                                sit.sitPlayer(event.getPlayer(), loc.add(0.5, 0.0, 0.5));
                            }
                        }
                    }
                }

                if (blockMaterialString.endsWith("ROD")) {
                    SitController sit = Plugin.sitController;
                    if (!sit.isSitting(event.getPlayer())) {
                        Location loc = event.getClickedBlock().getLocation();
                        if (loc.add(0.0, 1.0, 0.0).getBlock().isEmpty()) {
                            if (loc.add(0.0, 1.0, 0.0).getBlock().isEmpty()) {
                                loc = event.getClickedBlock().getLocation().subtract(0.0, 1.3, 0.0);
                                sit.sitPlayer(event.getPlayer(), loc.add(0.5, 0.0, 0.5));
                            }
                        }
                    }
                }
            } else {
            }
        }
    }

    @EventHandler
    public void onDismountPlayer(EntityDismountEvent event) {
        if (event.getEntity() instanceof Player) {
            SitController sit = Plugin.sitController;
            if (sit.isSitting(((Player) event.getEntity()).getPlayer())) {
                sit.delSitPlayer(((Player) event.getEntity()).getPlayer());
            }
        }
    }
}
