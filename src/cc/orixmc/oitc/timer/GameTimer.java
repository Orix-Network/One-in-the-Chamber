package cc.orixmc.oitc.timer;

import cc.orixmc.oitc.Oitc;
import cc.orixmc.oitc.broadcaster.Broadcast;
import cc.orixmc.oitc.profiles.Profile;
import cc.orixmc.oitc.server.ServerState;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;

public class GameTimer extends BukkitRunnable {
    @Getter private int left;
    private boolean forceStart;

    public void start() {
        this.runTaskTimer(Oitc.getInstance(), 20, 20);
    }

    @Override
    public void run() {
        --this.left;
        if (Oitc.getInstance().getServerManager().getServerState() == ServerState.WAITING) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                Profile profile = Oitc.getInstance().getProfileManager().getProfile(player.getUniqueId());
                Broadcast.getByName("Waiting").onTick(player, profile);
            }
            if (Bukkit.getOnlinePlayers().size() < 4 && !this.forceStart) {
                this.left = 60;
            }
            if (this.left <= 0) {
                this.left = 10;
                Oitc.getInstance().getServerManager().setServerState(ServerState.STARTING);
            }
        } else if (Oitc.getInstance().getServerManager().getServerState() == ServerState.STARTING) {
            if (Bukkit.getOnlinePlayers().size() < 4 && !this.forceStart) {
                Oitc.getInstance().getServerManager().setServerState(ServerState.WAITING);
                this.left = 60;
            }
            if (this.left <= 0) {
                this.left = 600;
                Oitc.getInstance().getServerManager().setServerState(ServerState.GAME);
            }
        }
    }


}
