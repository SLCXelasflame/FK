package fr.xelasflame.fk;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TimerGame {


    public static int secondsRemaining = 0;

    public static int actuday = 1;

    public static boolean pvp = false;
    public static boolean nether = false;
    public static boolean assaut = false;

    public static Scoreboard scoreboard = TeamScoreboard.displayScoreboard();
    public static Objective objective = scoreboard.registerNewObjective(ChatColor.RED + "Fallen Kingdom", "dummy");
    public static Team timer = scoreboard.registerNewTeam("timer");

    public static Team jours = scoreboard.registerNewTeam("jours");


    public static void loadobjective(){
        actuday = 1;
        secondsRemaining =0;
        timer.addEntry("Temps : ");
        jours.addEntry("Jours ");
        objective.getScore("Temps : ").setScore(0);
        objective.getScore("Jours ").setScore(1);
        objective.setDisplayName(ChatColor.GREEN +"FK");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        jours.setSuffix(actuday +"");
    }




    public static void start() {
        loadobjective();
        new BukkitRunnable() {

            @Override
            public void run() {
                if (GameManager.winner.equalsIgnoreCase("")) {

                    if (secondsRemaining < 1200) {
                        updateScoreboard();
                        secondsRemaining++;
                    }

                    else {
                        secondsRemaining = 0;
                        updateScoreboard();
                        actuday++;
                        if (actuday == 2){
                            pvp = true;
                            Bukkit.broadcastMessage("Le pvp est activé");
                        } else if (actuday == 3){
                            nether = true;
                            BaseManager.setnether();
                            Bukkit.broadcastMessage("Le nether est activé");
                        }
                        else if (actuday == 5){
                            assaut = true;
                            Bukkit.broadcastMessage("Les assauts sont activé");
                        }
                        jours.setSuffix(actuday + "");
                    }
                }

                else {
                    Start.sendTitleToAllPlayers("La game est terminee, victoire des " + GameManager.winner,"" , 10, 30, 10);
                    cancel();
                }
            }
        }.runTaskTimer(Main.getPlugin(Main.class), 0L, 20L);
    }

    public static void updateScoreboard() {


        int minutes =secondsRemaining  / 60;
        int remainingSeconds = secondsRemaining % 60;

        String formattedTime = String.format("%02d:%02d", minutes, remainingSeconds);
        timer.setSuffix(formattedTime);
        for (Player player : Bukkit.getOnlinePlayers()) {
            TeamScoreboard.addteam(player, Start.viewteam(player));
            player.setScoreboard(scoreboard);
        }
    }


}



