package cc.orixmc.oitc.server;

import cc.orixmc.oitc.Oitc;
import cc.orixmc.oitc.broadcaster.Broadcast;
import cc.orixmc.oitc.profiles.Profile;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Arrays;

public class ServerManager {
    private int serverState;

    public void setServerState(ServerState state){
        switch (state) {
            default:
            case WAITING:
                this.serverState = 0;
                break;
            case STARTING:
                this.serverState = 1;
                break;
            case GAME:
                this.serverState = 2;
                break;
        }
    }

    public int getServerStateInt(){
        return this.serverState;
    }

    public ServerState getServerState() {
        switch(this.serverState){
            default:
            case 0:
                return ServerState.WAITING;
            case 1:
                return ServerState.STARTING;
            case 2:
                return ServerState.GAME;
        }
    }

    public void setServerState(int i){
        if(i >= 0 && i < 3){
            this.serverState = i;
        }
    }

    public void startServer() {
        Bukkit.getOnlinePlayers().forEach(player -> player.teleport(new Location(Bukkit.getWorld("world"), 0, 0,0 )));
        Bukkit.getOnlinePlayers().forEach(player -> Oitc.getInstance().getProfileManager().getProfile(player.getUniqueId()).setKillstreak(0));
        Broadcast broadcast = Broadcast.getByName("Waiting");
        //todo timer for waiting

    }

    public void startStarting() {
        //todo random positions
        Broadcast broadcast = Broadcast.getByName("Starting");
    }
}
