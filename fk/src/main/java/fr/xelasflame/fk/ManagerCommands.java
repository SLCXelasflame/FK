package fr.xelasflame.fk;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Wool;
import org.bukkit.scheduler.BukkitRunnable;

public class ManagerCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String index, String[] label) {
        if( sender instanceof Player){
            Player player = ((Player) sender).getPlayer();
            if (index.equalsIgnoreCase("fk")){
            if (player.isOp()){
                if (label[0].equalsIgnoreCase("start")){
                    startCountdown();
                } else if (label[0].equalsIgnoreCase("setteam")) {
                    if (label.length != 2){
                        player.sendMessage("Il faut définir la team");
                    }
                    else {
                        GameManager.saveteamco(player, label[1]);
                        player.sendMessage("La team " + label[1] +" a bien ete enregistree");
                    }}
                    else if (label[0].equalsIgnoreCase("winner")) {
                        if (label.length != 2){
                            player.sendMessage("Il faut définir les vainqueurs");
                        }
                        else {
                            GameManager.winner = label[1];
                        }
                }
            }} else {
                player.sendMessage("Vous ne pouvez pas executer cette commande");
            }


        }
        return false;
    }



    private void startCountdown() {

        new BukkitRunnable() {
            int count = 6;

            @Override
            public void run() {
                if (count == 6){
                    Start.sendTitleToAllPlayers("La game va bientôt se lancée", "", 10, 30, 10);
                    count --;
                }
                else if (count > 0) {
                    Start.sendTitleToAllPlayers( String.valueOf(count),"", 10, 30, 10);
                    count--;
                } else {
                    Start.sendTitleToAllPlayers("Bonne gamme a tous","" , 10, 30, 10);
                    TimerGame.start();
                    GameManager.gamestart();
                    cancel();
                }
            }
        }.runTaskTimer(Main.getPlugin(Main.class), 0L, 20L);

    }

}


