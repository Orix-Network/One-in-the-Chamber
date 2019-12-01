package cc.orixmc.oitc.profiles;

import cc.orixmc.oitc.Oitc;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class ProfileListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        ProfileManager.userExists(new ProfileManager.Callback<HashMap<String, Boolean>>() {
            @Override
            public void onSuccess(HashMap<String, Boolean> done) {
                if (done.get(player.getUniqueId().toString()) != Boolean.TRUE) {
                    Oitc.getInstance().getProfileManager().createStatsUser(player);
                    Oitc.getInstance().getProfileManager().addProfile(player.getUniqueId(), new Profile(player.getUniqueId()));
                    Oitc.getInstance().getProfileManager().load(player);
                } else {
                    Oitc.getInstance().getProfileManager().load(player);
                    Oitc.getInstance().getProfileManager().addProfile(player.getUniqueId(), Oitc.getInstance().getProfileManager().getProfile(player.getUniqueId()));
                }
            }

            @Override
            public void onFailure(Throwable cause) {
                Bukkit.getLogger().severe(cause.getMessage());
            }
        }, player.getUniqueId().toString());
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Oitc.getInstance().getProfileManager().unload(player);

    }
}
