package cc.orixmc.oitc.broadcaster.impl;

import cc.orixmc.oitc.Oitc;
import cc.orixmc.oitc.broadcaster.Broadcast;
import cc.orixmc.oitc.profiles.Profile;
import cc.orixmc.oitc.profiles.ProfileManager;
import cc.orixmc.oitc.server.ServerState;
import cc.orixmc.oitc.util.StringUtil;
import org.bukkit.entity.Player;

public class WaitingBroadcast extends Broadcast {

    public WaitingBroadcast() {
        super("waiting");
    }

    @Override
    public String getBroadcastMessage() {
        return StringUtil.format("&cWaiting for players to connect &7(&6" + Oitc.getInstance().getProfileManager().getProfiles().size() + "&7/ &630");
    }

    @Override
    public void onTick(Player player, Profile profile) {
        player.sendMessage(StringUtil.format(getBroadcastMessage()));
    }
}
