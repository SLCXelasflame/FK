package fr.xelasflame.fk;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class GameEvent implements Listener {



    public Inventory team = Bukkit.createInventory(null, 9, "Team selector");

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if (!GameManager.start) {
            Player player = event.getPlayer();
            player.sendMessage("Psartek mon reuf bon fk a toi");
            player.getInventory().clear();
            player.setItemInHand(ItemManager.Team_Selector);
            String team = Start.viewteam(player);
            if (team != null) {
                TeamScoreboard.addteam(player, team);
            }
            player.sendMessage(team);
        }
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent event){


        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack it = event.getItem();

        if (it == null) return;

        if (it.equals(ItemManager.Team_Selector)){
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK){



                team.setItem(0, ItemManager.red);
                team.setItem(1, ItemManager.yellow);
                team.setItem(2, ItemManager.blue);
                team.setItem(3, ItemManager.green);
                team.setItem(4, ItemManager.dieu);
                player.openInventory(team);
            }
        }

    }

    @EventHandler
    public void onClick(InventoryClickEvent event){

        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();
        if (current == null) return;

        if (inv.equals(team)){

            event.setCancelled(true);

            if (current.isSimilar(ItemManager.red)){
                player.closeInventory();
                player.sendMessage("§e§lTu vas rejoindre la team rouge");
                Start.addTeam("Rouge", player);
            }
            else if (current.isSimilar(ItemManager.yellow)){
                player.closeInventory();
                player.sendMessage("§e§lTu vas rejoindre la team jaune");
                Start.addTeam("Jaune", player);
            }

            else if (current.isSimilar(ItemManager.blue)){
                player.closeInventory();
                player.sendMessage("§e§lTu vas rejoindre la team bleu");
                Start.addTeam("Bleu", player);
            }

            else if (current.isSimilar(ItemManager.green)){
                player.closeInventory();
                player.sendMessage("§e§lTu vas rejoindre la team verte");
                Start.addTeam("Vert", player);
            }
            else if (current.isSimilar(ItemManager.dieu)){
                player.closeInventory();
                player.sendMessage("§e§lTu vas rejoindre la team Dieu");
                Start.addTeam("Dieu", player);
            }



    }



}
@EventHandler
    public void onCLick(PlayerInteractEvent event){
        Block block = event.getClickedBlock();
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if (item == null) return;
        if (item.getType().equals(Material.FLINT_AND_STEEL)) {
            if (block.getType().equals(Material.TNT)) {
                if (!TimerGame.assaut) {
                        player.sendMessage("Tu ne peux pas encore utiliser de tnt");
                        event.setCancelled(true);
                    }
                } else {
                    event.setCancelled(true);
                    player.sendMessage("Les briquets sont désactivés en dehors de l allumage des tnts");
                }
            } else if (item.getType().equals(Material.LAVA_BUCKET)) {
                event.setCancelled(true);
                player.sendMessage("les sceaux de lave sont désactivés");
            }


}
    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        if (event.getRecipe().getResult().getType() == Material.FISHING_ROD) {
            Player player = (Player) event.getWhoClicked();
            player.sendMessage("La création de cannes à pêche est désactivée.");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (block.getType().equals(Material.TNT)){
            if (!TimerGame.assaut){
                event.setCancelled(true);
                player.sendMessage("Tu ne peux pas placer de tnt avant les assauts");
            }
        } else {
            if (BaseManager.notinBase(player, block)){
                player.sendMessage("Tu ne peux pas poser de blocks en dehors de ta base");
                event.setCancelled(true);
            }
        }
    }

}




