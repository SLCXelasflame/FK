package fr.xelasflame.fk;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Wool;

import java.util.HashMap;


public class BaseManager {
    public static HashMap<String, DyeColor> color = new HashMap<>();
    public static void loadcolor(){
        color.put("Rouge", DyeColor.RED);
        color.put("Bleu", DyeColor.BLUE);
        color.put("Vert", DyeColor.GREEN);
        color.put("Jaune", DyeColor.YELLOW);

    }
    public static void genbase(String team){
        Location base = GameManager.loadteamco(team);
        if (base != null) {
            for (int x = ((int) base.getX()) -14; x <= base.getX() +14; x++){
                Location loc1 = new Location(Bukkit.getWorld("world"), x, base.getY(), base.getZ() - 14, 0, 0);
                Location loc2 = new Location(Bukkit.getWorld("world"), x, base.getY(), base.getZ() + 14, 0, 0);
                loc1.getBlock().setType(Material.COBBLESTONE);
                loc2.getBlock().setType(Material.COBBLESTONE);
            }
            for (int z = ((int) base.getZ()) -14; z <= base.getZ() +14; z++){
                Location loc1 = new Location(Bukkit.getWorld("world"), base.getX() -14, base.getY(), z, 0, 0);
                Location loc2 = new Location(Bukkit.getWorld("world"), base.getX()+14, base.getY(), z, 0, 0);
                loc1.getBlock().setType(Material.COBBLESTONE);
                loc2.getBlock().setType(Material.COBBLESTONE);
            }
            base.getBlock().setType(Material.FENCE);
            for (int y = ((int) base.getY()) +1 ; y <= base.getY() +4; y++){
                Location loc1 = new Location(Bukkit.getWorld("world"), base.getX()-1, y, base.getZ(), 0, 0);
                Location loc2 = new Location(Bukkit.getWorld("world"), base.getX()+1, y, base.getZ(), 0, 0);
                Location loc3 = new Location(Bukkit.getWorld("world"), base.getX(), y, base.getZ(), 0, 0);
                loc3.getBlock().setType(Material.FENCE);
                loc1.getBlock().setType(Material.WOOL);
                BlockState blockState = loc1.getBlock().getState();
                MaterialData blockData = blockState.getData();
                Wool data = (Wool) blockData;
                data.setColor(color.get(team));
                blockState.setData(data);
                blockState.update(true);
                loc2.getBlock().setType(Material.WOOL);
                BlockState blockState2 = loc2.getBlock().getState();
                MaterialData blockData2 = blockState2.getData();
                Wool data2 = (Wool) blockData2;
                data2.setColor(color.get(team));
                blockState2.setData(data2);
                blockState2.update(true);
            }
        }
    else {
        Bukkit.broadcastMessage("La base " + team +" n est pas enregistree");
        }}



    public static Boolean notinBase(Player player, Block block){
        String team = Start.viewteam(player);
        if (team != null) {
            Location loc = block.getLocation();
            Location base = GameManager.loadteamco(team);
            if (player.getGameMode().equals(GameMode.SURVIVAL) && loc.getWorld().equals(Bukkit.getWorld("world"))) {
                if (base != null) {
                    if (base.getX() - 14 <= loc.getX() && loc.getX() <= base.getX() + 14) {
                        if (base.getZ() - 14 <= loc.getZ() && loc.getZ() <= base.getZ() + 14) {
                            return false;

                        } else {
                            return true;
                        }
                    } else {
                        return true;
                    }
                } else {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage("Tu n'as pas de base");
                    return true;
                }
            } else {
                return false;
            }
        }
        else {
            player.sendMessage("Tu n'as pas de team");
            player.setGameMode(GameMode.SPECTATOR);
            return true;
        }
    }

    public static void setnether(){
        Location loc = GameManager.loadteamco("nether");
        if (loc != null){
            Block fire = loc.getBlock();
            fire.setType(Material.FIRE);
        }
    }
}
