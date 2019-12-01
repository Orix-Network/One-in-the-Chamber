package cc.orixmc.oitc;

import cc.orixmc.oitc.listeners.AttackEvent;
import cc.orixmc.oitc.listeners.JoinEvent;
import cc.orixmc.oitc.profiles.ProfileManager;
import cc.orixmc.oitc.server.ServerManager;
import cc.orixmc.oitc.util.MySQL;
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

    public void onEnable() {
        instance = this;
        registerManagers();
        registerCommands();
        registerEvents();
    }

    public void onDisable() {

        instance = null;
    }

    private void registerManagers() {
        profileManager = new ProfileManager();
        serverManager = new ServerManager();
        mySQL = new MySQL(this);
    }

    private void registerEvents() {
        Arrays.asList(new AttackEvent(), new JoinEvent()).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void registerCommands() {
        //Arrays.asList(getCommand("otic"), getCommand("dev")).forEach(command -> command.setExecutor());
    }

    public static Oitc getInstance() {
        return instance;
    }
}
