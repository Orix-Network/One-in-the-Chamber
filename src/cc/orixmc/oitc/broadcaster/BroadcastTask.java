package cc.orixmc.oitc.broadcaster;

import cc.orixmc.oitc.Oitc;
import cc.orixmc.oitc.profiles.Profile;
import cc.orixmc.oitc.server.ServerState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BroadcastTask extends BukkitRunnable {
    private int time = 0;
    // the purpose of the class too is to make it easier for you, it will run the onTick method provided in the Quest class every second IF the active quest of the player is valid

    @Override
    public void run() {
        time = time + 1;
        for (Player player : Bukkit.getOnlinePlayers()) {

            Profile profile = Oitc.getInstance().getProfileManager().getProfile(player.getUniqueId());
            Broadcast broadcast = Broadcast.getByName(Oitc.getInstance().getServerManager().getServerState().toString());
            if (time == 60) {
                if (broadcast != null) {
                    broadcast.onTick(player, profile);
                }

            }
        }
    }
}
