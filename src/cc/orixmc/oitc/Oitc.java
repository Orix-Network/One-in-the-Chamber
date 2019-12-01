package cc.orixmc.oitc;

import cc.orixmc.oitc.commands.OITCCommand;
import cc.orixmc.oitc.listeners.AttackEvent;
import cc.orixmc.oitc.listeners.JoinEvent;
import cc.orixmc.oitc.profiles.ProfileManager;
import cc.orixmc.oitc.scoreboard.Board;
import cc.orixmc.oitc.server.ServerManager;
import cc.orixmc.oitc.server.ServerState;
import cc.orixmc.oitc.timer.GameTimer;
import cc.orixmc.oitc.util.MySQL;
import io.github.thatkawaiisam.assemble.Assemble;
import io.github.thatkawaiisam.assemble.AssembleStyle;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

@Getter
public class Oitc extends JavaPlugin {
    private static Oitc instance;
    private ProfileManager profileManager;
    private MySQL mySQL;
    private ServerManager serverManager;
    private GameTimer gameTimer;

    public void onEnable() {
        instance = this;
        registerManagers();
        registerCommands();
        registerEvents();
        instance.getServerManager().setServerState(ServerState.WAITING);
        Assemble assemble = new Assemble(this, new Board());
        assemble.setTicks(2);
        assemble.setAssembleStyle(AssembleStyle.MODERN);
    }

    public void onDisable() {

        instance = null;
    }

    private void registerManagers() {
        profileManager = new ProfileManager();
        serverManager = new ServerManager();
        gameTimer = new GameTimer();
        mySQL = new MySQL(this);
    }

    private void registerEvents() {
        Arrays.asList(new AttackEvent(), new JoinEvent()).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void registerCommands() {
        getCommand("oitc").setExecutor(new OITCCommand());
    }

    public static Oitc getInstance() {
        return instance;
    }
}
