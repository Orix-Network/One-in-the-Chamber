package cc.orixmc.oitc.listeners;

import cc.orixmc.oitc.Oitc;
import cc.orixmc.oitc.server.ServerManager;
import cc.orixmc.oitc.server.ServerState;
import cc.orixmc.oitc.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Oitc.getInstance().getServerManager().getServerState() == ServerState.WAITING) {
            player.sendMessage(StringUtil.format("&eYou have joined the lobby. &7(&6" + Bukkit.getOnlinePlayers().size() + "&7/ &630&7)"));
            if (Bukkit.getOnlinePlayers().size() == 4) {
                Bukkit.getOnlinePlayers().forEach(instance -> instance.sendMessage(StringUtil.format("\n&eStarting in 5s\n")));
                Oitc.getInstance().getServerManager().setServerState(ServerState.STARTING);
            }
        }
    }
}
