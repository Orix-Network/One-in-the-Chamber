package cc.orixmc.oitc.timer;

import cc.orixmc.oitc.Oitc;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class TimerManager {

    private int totaltime = 0;

    public void startTime() {
        Bukkit.getScheduler().runTask(Oitc.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                totaltime = totaltime + 1;
            }
        });
    }

    public void getTotalTime() {

    }
}
