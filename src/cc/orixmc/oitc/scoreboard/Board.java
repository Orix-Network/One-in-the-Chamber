package cc.orixmc.oitc.scoreboard;

import cc.orixmc.oitc.Oitc;
import cc.orixmc.oitc.profiles.Profile;
import cc.orixmc.oitc.server.ServerState;
import io.github.thatkawaiisam.assemble.AssembleAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Board implements AssembleAdapter {

    @Override
    public String getTitle(Player player) {
        if (Oitc.getInstance().getServerManager().getServerState() == ServerState.WAITING) {
            return "Waiting...";
        }
        if (Oitc.getInstance().getServerManager().getServerState() == ServerState.STARTING) {
            return "Starting...";
        }
        if (Oitc.getInstance().getServerManager().getServerState() == ServerState.GAME) {
            return "&eOITC";
        }
        return null;
    }

    @Override
    public List<String> getLines(Player player) {
        Profile profile = Oitc.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (Oitc.getInstance().getServerManager().getServerState() == ServerState.WAITING) {
            final List<String> waiting = new ArrayList<>();
            waiting.add("Players: " + Bukkit.getOnlinePlayers().size());
            waiting.add("Needed: 4");
            return waiting;
        }
        if (Oitc.getInstance().getServerManager().getServerState() == ServerState.STARTING) {
            final List<String> starting = new ArrayList<>();
            starting.add("Starting in " + Oitc.getInstance().getGameTimer().getLeft());
            return starting;
        }
        if (Oitc.getInstance().getServerManager().getServerState() == ServerState.GAME) {
            final List<String> game = new ArrayList<>();
            game.add("Time &e" + Oitc.getInstance().getGameTimer().getLeft());
            game.add("Kills: &e" + profile.getKills());
            game.add("Deaths: &e" + profile.getDeaths());
            game.add("Kill-Streak: &e" + profile.getKills());
            switch (profile.getLeaderboard()) {
                case 0:
                    game.add("Place: &e1st");
                case 1:
                    game.add("Place: &e2nd");
                case 2:
                    game.add("Place: &e3rd");
                case 3:
                    game.add("Place: &e4th");
                case 4:
                    game.add("Place: &e5th");
                case 5:
                    game.add("Place: &e6th");
                case 6:
                    game.add("Place: &e7th");
                case 7:
                    game.add("Place: &e8th");
                case 8:
                    game.add("Place: &e9th");
                case 9:
                    game.add("Place: &e10th");
                case 10:
                    game.add("Place: &e11th");
                case 11:
                    game.add("Place: &e12th");
                case 12:
                    game.add("Place: &e13th");
                case 13:
                    game.add("Place: &e14th");
                case 14:
                    game.add("Place: &e15th");
                case 15:
                    game.add("Place: &e16th");
                case 16:
                    game.add("Place: &e17th");
                case 17:
                    game.add("Place: &e18th");
                case 18:
                    game.add("Place: &e19th");
                case 19:
                    game.add("Place: &e20th");
                case 20:
                    game.add("Place: &e21th");
                case 21:
                    game.add("Place: &e22th");
                case 22:
                    game.add("Place: &e23th");
                case 23:
                    game.add("Place: &e24th");
                case 24:
                    game.add("Place: &e25th");
                case 25:
                    game.add("Place: &e26th");
                case 26:
                    game.add("Place: &e27th");
                case 27:
                    game.add("Place: &e28th");
                case 28:
                    game.add("Place: &e29th");
                case 29:
                    game.add("Place: &e30th");
            }
            return game;
        }
        return null;
    }
}
