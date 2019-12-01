package cc.orixmc.oitc.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Iterator;

public class LocationManager {
    private int a = 0;

    public Location getSpawn() {
        return new Location(Bukkit.getWorld("world"), 0, 0, 0);
    }

    public Location getWaitingLobby() {
        return new Location(Bukkit.getWorld("world"), 0, 0, 0);
    }

    public void teleportPlayers() {
        Iterator iterator = Bukkit.getOnlinePlayers().iterator();
        if (iterator.hasNext()) {
            Player player = (Player) iterator.next();
            if (a == 0) {
                player.teleport(new Location(Bukkit.getWorld("world"), 0, 0, 0));
                a = a + 1;
            }
        }
    }
}
