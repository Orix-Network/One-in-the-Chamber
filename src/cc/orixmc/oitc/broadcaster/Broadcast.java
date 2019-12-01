package cc.orixmc.oitc.broadcaster;

import cc.orixmc.oitc.broadcaster.impl.WaitingBroadcast;
import cc.orixmc.oitc.profiles.Profile;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public abstract class Broadcast {
    private static Map<String, Broadcast> byName = new HashMap<>();
    private static final Broadcast STARTING = new WaitingBroadcast();

    private String name;

    public Broadcast(String name) {
        this.name = name;
        byName.put(name, this);
    }

    public static Broadcast getByName(String name) {
        return byName.get(name);
    }

    public abstract String getBroadcastMessage();

    public void onTick(Player player, Profile profile) {

    }
}
