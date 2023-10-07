package fr.xelasflame.fk;

import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GameManager {

    public static String winner = "";
    public static Boolean start = false;
    public static File file = new File(Main.getPlugin(Main.class).getDataFolder(), "playersinfo.yml");
    public static YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

    public static void giveStuff(Player player){
        player.getInventory().clear();
        clearAllEffects(player);
        player.setGameMode(GameMode.SURVIVAL);

    }
    public static void clearAllEffects(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }

    public static void gamestart() {
        BaseManager.genbase("Rouge");
        BaseManager.genbase("Bleu");
        BaseManager.genbase("Vert");
        BaseManager.genbase("Jaune");
        start = true;
        winner = "";
        Location loc = null;
        for (Player player : Bukkit.getOnlinePlayers()) {
            giveStuff(player);
            String team = Start.viewteam(player);
            if (team != null) {
                loc = loadteamco(team);

            }
            if (loc != null) {
                player.teleport(loc);
            }
            else{
                player.setGameMode(GameMode.SPECTATOR);
            }
        }

    }


    public static void saveteamco(Player player, String team) {
        Location location = player.getLocation();

        String locationString = location.getWorld().getName() + ","
                + location.getX() + ","
                + location.getY() + ","
                + location.getZ() + ","
                + location.getYaw() + ","
                + location.getPitch();

        configuration.set(team, locationString);
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Location loadteamco(String team) {
        String locationString = configuration.getString(team);

        if (locationString != null) {
            String[] parts = locationString.split(",");
            if (parts.length == 6) {
                String worldName = parts[0];
                double x = Double.parseDouble(parts[1]);
                double y = Double.parseDouble(parts[2]);
                double z = Double.parseDouble(parts[3]);
                float yaw = Float.parseFloat(parts[4]);
                float pitch = Float.parseFloat(parts[5]);

                return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
            }
        }

        return null; }}
