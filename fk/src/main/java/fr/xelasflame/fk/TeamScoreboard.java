package fr.xelasflame.fk;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Objects;

public class TeamScoreboard {
    public static Scoreboard displayScoreboard(){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Team rouge = board.registerNewTeam("Rouge");
        rouge.setPrefix(ChatColor.RED + "[Rouge] ");

        Team bleu = board.registerNewTeam("Bleu");
        bleu.setPrefix(ChatColor.BLUE + "[Bleu] ");

        Team vert = board.registerNewTeam("Vert");
        vert.setPrefix(ChatColor.GREEN + "[Vert] ");

        Team jaune = board.registerNewTeam("Jaune");
        jaune.setPrefix(ChatColor.YELLOW + "[Jaune] ");


        Team dieu = board.registerNewTeam("Dieu");
        dieu.setPrefix(ChatColor.BOLD + "[Dieu] ");
        return board;
    }

    public static void addteam(Player player, String team){
        Scoreboard scoreboard = displayScoreboard();
        Objects.requireNonNull(scoreboard.getTeam(team)).addEntry(player.getName());

        player.setScoreboard(scoreboard);

    }

    
}
