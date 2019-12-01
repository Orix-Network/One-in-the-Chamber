package cc.orixmc.oitc.profiles;

import cc.orixmc.oitc.Oitc;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileManager {
    private final String INSERT = "INSERT INTO profiles VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    @Getter private Map<UUID, Profile> profiles = new HashMap<>();

    public void addProfile(UUID uuid, Profile profile) {
        profiles.put(uuid, profile);
    }

    public Profile getProfile(UUID uuid) {
        return profiles.get(uuid);
    }

    public boolean hasProfile(UUID uuid) {
        return profiles.containsKey(uuid);
    }

    public void load(Player player) {
        Profile playerProfile = new Profile(player.getUniqueId());
        profiles.put(player.getUniqueId(), playerProfile);
        Oitc.getInstance().getProfileManager().addProfile(player.getUniqueId(), playerProfile);
        final String SELECT = "SELECT NAME, BALANCE FROM profiles WHERE UUID=?";

        Bukkit.getScheduler().runTaskAsynchronously(Oitc.getInstance(), () -> {
            try {
                PreparedStatement preparedStatement = Oitc.getInstance().getMySQL().getConnection().prepareStatement(SELECT);
                preparedStatement.setString(1, player.getUniqueId().toString());

                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    playerProfile.setName(rs.getString("NAME"));
                    playerProfile.setKills(rs.getInt("KILLS"));
                    playerProfile.setDeaths(rs.getInt("DEATHS"));
                    playerProfile.setKillstreak(rs.getInt("KILLSTREAK"));
                }

            } catch (SQLException e) {

            }
        });
    }

    public void unload(Player player) {
        Profile playerProfile = Oitc.getInstance().getProfileManager().getProfile(player.getUniqueId());

        try {
            PreparedStatement preparedStatement = Oitc.getInstance().getMySQL().getConnection().prepareStatement("UPDATE profiles SET NAME = ? , BALANCE = ? WHERE UUID = ?");
            preparedStatement.setString(1, player.getName());
            preparedStatement.setInt(9, playerProfile.getKills());
            preparedStatement.setInt(10, playerProfile.getDeaths());
            preparedStatement.setInt(11, playerProfile.getKillstreak());
            preparedStatement.setString(23, player.getUniqueId().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        profiles.remove(player.getUniqueId());
    }


    public boolean existsUser(Player player) {
        PreparedStatement ps;
        try {
            ps = Oitc.getInstance().getMySQL().getConnection().prepareStatement("SELECT * FROM profiles WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            Bukkit.getLogger().severe(e.getMessage());
            return false;
        }
    }

    public static void userExists(final Callback<HashMap<String, Boolean>> callback,
                                  final String uuid) {
        // Create a async Bukkit scheduler
        Bukkit.getScheduler().runTaskAsynchronously(Oitc.getInstance(), () -> {
            final HashMap<String, Boolean> result = new HashMap<>();
            PreparedStatement ps;

            try {
                ps = Oitc.getInstance().getMySQL().getConnection().prepareStatement("SELECT * FROM profiles WHERE UUID = ?");
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();

                result.put(uuid, rs.next());

            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Create a new async Bukkit scheduler
            Bukkit.getScheduler().runTaskAsynchronously(Oitc.getInstance(), () -> {
                try {
                    callback.onSuccess(result);
                } catch (Exception ex) {
                    callback.onFailure(ex.getCause());
                }
            });
        });
    }

    public void createStatsUser(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(Oitc.getInstance(), () -> {
            try {
                PreparedStatement preparedStatement = Oitc.getInstance().getMySQL().getConnection().prepareStatement(INSERT + " ON DUPLICATE KEY UPDATE UUID='" + player.getUniqueId() + "'");
                preparedStatement.setString(1, player.getUniqueId().toString());
                preparedStatement.setString(2, player.getName());
                //TODO rest of this shit
                preparedStatement.execute();
                preparedStatement.executeUpdate();
                Oitc.getInstance().getProfileManager().load(player);
            } catch (SQLException e) {
                Bukkit.getLogger().severe(e.getMessage());
            }
        });
    }

    public interface Callback<T> {
        void onSuccess(T done);

        void onFailure(Throwable cause);
    }
}
