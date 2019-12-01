package cc.orixmc.oitc.listeners;

import cc.orixmc.oitc.Oitc;
import cc.orixmc.oitc.profiles.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AttackEvent implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Arrow) {
            Arrow f = (Arrow) event.getDamager();
            if (event.getEntity() instanceof Player) {
                Player shooter = (Player) f.getShooter();
                Player shot = ((Player) event.getEntity()).getPlayer();
                if (!shooter.getName().equals(shot.getName())) {
                  // shooter.sendMessage(ChatColor.RED + shot.getName() + ChatColor.GRAY + " (" + event.getDamage() + ")");
                    event.setDamage(100D);
                    //TODO get a random respawn location
                    Profile agr = Oitc.getInstance().getProfileManager().getProfile(shooter.getUniqueId());
                    Profile def = Oitc.getInstance().getProfileManager().getProfile(shot.getUniqueId());
                    agr.setKills(agr.getKills() + 1);
                    agr.setKillstreak(agr.getKillstreak() + 1);
                    def.setKillstreak(0);
                    def.setDeaths(def.getDeaths() + 1);
                    shooter.getInventory().addItem(new ItemStack(Material.ARROW));
                }
            }
        }
    }

    public Inventory getDefaultInvetnory() {
        Inventory i = Bukkit.createInventory(null, 9);
        ItemStack bow = new ItemStack(Material.BOW);
        ItemStack sword = new ItemStack(Material.STONE_SWORD);
        ItemStack arrow = new ItemStack(Material.ARROW);
        i.setItem(0, bow);
        i.setItem(1, sword);
        i.setItem(2, arrow);
        return i;
    }

    public void respawn(Player player) {
        player.spigot().respawn();
        player.getInventory().setContents(getDefaultInvetnory().getContents());
    }
}
