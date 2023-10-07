package fr.xelasflame.fk;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Start {


    public  static File file = new File(Main.getPlugin(Main.class).getDataFolder(), "playersinfo.yml");
    public static YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

    public static void addTeam(String team, Player player){
        if (player == null){
            return;
        }
        String pseudo = player.getName();

        config.set(pseudo, team);
        TeamScoreboard.addteam(player, team);

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void sendTitle(Player player, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        String command = "title " + player.getName() + " title " + "{\"text\":\"" + title + "\"}";

        player.getServer().dispatchCommand(player.getServer().getConsoleSender(), command);
    }


        public static void sendTitleToAllPlayers(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);
            }
        }
    public static String viewteam(Player player){
        return config.getString(player.getName()) ;
    }

public static ArrayList<Player> teamplayer(String team){
    ArrayList<Player> playerlist = new ArrayList<>();
    for (Player player : Bukkit.getOnlinePlayers()) {
        if (config.contains(player.getName())) {
            if (config.get(player.getName()).equals(team)) {
                playerlist.add(player);
            }
        }
    }
    return playerlist;
}


}

