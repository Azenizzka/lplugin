package me.azeniz.lplugin.sleep;

import me.azeniz.lplugin.Plugin;
import me.azeniz.lplugin.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.world.TimeSkipEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class SleepEvents implements Listener {
    Sleep sleep = new Sleep();

    @EventHandler
    public void onEnterBed(PlayerBedEnterEvent event) {
        new BukkitRunnable() {

            @Override
            public void run() {
                Player player = event.getPlayer();
                if (player.isSleeping()) {
                    player.setBedSpawnLocation(event.getBed().getLocation());
                    String nickname = event.getPlayer().getName();

                    int playersOnline = Plugin.instance.getServer().getWorld("world").getPlayers().size(); // всего игроков в World`е
                    float precent = 20f;
                    int need = (int) Math.ceil((precent * playersOnline)/100);

                    Util.sendBroadcast(nickname + " лег в кроватку, присоединяйтесь!");
                    sleep.addCount();
                    if (sleep.getCount() < need) {
                        Util.sendBroadcast("Всего спят: &c" + sleep.getCount() + "&7/&2" + (need));
                    } else {
                        Util.sendBroadcast("Всего спят: &2" + sleep.getCount() + "&7/&2" + (need));
                    }

                    if (sleep.getCount() >= need) {
                        Util.sendBroadcast("Ночь будет скоро пропущена..");
                        sleep.skipNight();
                        event.getPlayer().getWorld().setThundering(false);
                        event.getPlayer().getWorld().setStorm(false);
                    }
                }
            }

        }.runTaskLater(Plugin.instance, 1L);
    }

    @EventHandler
    public void onLeaveBed(PlayerBedLeaveEvent event) {
        long ticks = Plugin.instance.getServer().getWorld("World").getTime();
        if ((ticks >= 12543 && ticks < 23900) || Plugin.instance.getServer().getWorld("world").isThundering()) {
            sleep.delCount();
            String nickname = event.getPlayer().getName();
            int playersOnline = Plugin.instance.getServer().getWorld("world").getPlayers().size(); // всего игроков в World`е
            float precent = 20f;
            int need = (int) Math.ceil((precent * playersOnline)/100);
            Util.sendBroadcast(nickname + " передумал спать");
            if (sleep.getCount() < need) {
                Util.sendBroadcast("Всего спят: &c" + sleep.getCount() + "&7/&2" + (need));
            } else {
                Util.sendBroadcast("Всего спят: &2" + sleep.getCount() + "&7/&2" + (need));
            }
        }
    }

    @EventHandler
    public void test(TimeSkipEvent event) {

    }
}
