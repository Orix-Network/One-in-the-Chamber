package cc.orixmc.oitc.util;

import cc.orixmc.oitc.Oitc;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQL {

    public String host = Oitc.getInstance().getConfig().getString("MySQL.host");
    public String port = Oitc.getInstance().getConfig().getString("MySQL.port");
    public String database = Oitc.getInstance().getConfig().getString("MySQL.database");
    public String username = Oitc.getInstance().getConfig().getString("MySQL.username");
    public String password = Oitc.getInstance().getConfig().getString("MySQL.password");

    private Connection con;
    private ConsoleCommandSender console = Bukkit.getConsoleSender();

    @Getter
    Oitc instance;

    public MySQL(Oitc plugin) {
        this.instance = plugin;
    }

    // connect
    public void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
                console.sendMessage(StringUtil.format("&7A secure connection to the MySQL has &asuccessfully &7been established."));
                PreparedStatement preparedStatement = this.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS profiles (UUID VARCHAR(36)," +
                        "NAME TEXT, KILLS INT(0), DEATHS INT(O), KILLSTREAK(0), LEADERBORD INT(0)" +
                        "PRIMARY KEY (UUID))");

                preparedStatement.executeUpdate();
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                Bukkit.getLogger().severe(e.getMessage());
            }
        }
    }

    // disconnect
    public void disconnectProfiles() {
        if (isConnected()) {
            try {
                con.close();
                console.sendMessage(StringUtil.format("&7The secure connection to MySQL has &csuccessfully &7been closed."));
            } catch (SQLException e) {
            }
        }
    }


    // isConnected
    public boolean isConnected() {
        return (con != null);
    }

    // getConnection
    public Connection getConnection() {
        return con;
    }

    // isConnected
    public boolean isConnectedClan() {
        return (con != null);
    }

    // getConnection
    public Connection getConnectionClan() {
        return con;
    }
}