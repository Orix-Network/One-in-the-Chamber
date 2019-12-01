package cc.orixmc.oitc.profiles;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class Profile {
    private UUID uuid;
    private String name;
    private int kills, deaths, killstreak, leaderboard;

    public Profile(UUID uuid) {
        this.uuid = uuid;
    }
}
