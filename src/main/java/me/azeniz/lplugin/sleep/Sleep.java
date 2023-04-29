package me.azeniz.lplugin.sleep;

import me.azeniz.lplugin.Plugin;
import me.azeniz.lplugin.util.Util;
import org.bukkit.scheduler.BukkitRunnable;

public class Sleep {
    int count;
    boolean isSkiping = false;

    public int getCount() {
        return count;
    }

    public void addCount() {
        this.count++;
    }

    public void delCount() {
        this.count--;
    }

    public void reset() {
        this.count = 0;
        this.isSkiping = false;
    }

    public boolean isSkiping() {
        return isSkiping;
    }

    public void skipNight() {
        if (!isSkiping) {
            isSkiping = true;
            long ticks = Plugin.instance.getServer().getWorld("World").getTime();
            final int[] counter = {(int) ticks};
            new BukkitRunnable() {
                @Override
                public void run() {
                    Plugin.instance.getServer().getWorld("World").setTime(counter[0]);
                    counter[0] = counter[0]+50;
                    if (counter[0] >= 24000) {
                        Util.sendBroadcast("Ночь была пропущена, всем доброе утро!");
                        Plugin.instance.getServer().getWorld("World").setThundering(false);
                        reset();
                        cancel();
                    }
                }
            }.runTaskTimer(Plugin.instance, 0L, 1L);
        }
    }
}
