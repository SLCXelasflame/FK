package fr.xelasflame.fk;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class ItemManager {


    public static ItemStack Team_Selector;
    public static ItemStack red;
    public static ItemStack blue;
    public static ItemStack green;
    public static ItemStack yellow;
    public static ItemStack dieu;




    public static void init() {
        createTeam_Selector();
        createred();
        createblue();
        creategreen();
        createyellow();
        createdieu();
    }

    private static void createTeam_Selector() {
        ItemStack item = new ItemStack(Material.COMPASS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Team selector");
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        item.setItemMeta(meta);
        Team_Selector = item;
    }
    private static void createdieu() {
        ItemStack item = new ItemStack(Material.WOOL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BOLD + "Dieu");
        item.setItemMeta(meta);
        dieu = item;
    }

    private static void createred() {
        ItemStack item = new ItemStack(Material.WOOL, 1, (short) 14);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Equipe rouge");
        item.setItemMeta(meta);
        red = item;
    }
    private static void createblue() {
        ItemStack item = new ItemStack(Material.WOOL, 1, (short) 3);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_BLUE + "Equipe bleu");
        item.setItemMeta(meta);
        blue = item;
    }
    private static void creategreen() {
        ItemStack item = new ItemStack(Material.WOOL, 1, (short) 13);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Equipe vert");
        item.setItemMeta(meta);
        green = item;
    }
    private static void createyellow() {
        ItemStack item = new ItemStack(Material.WOOL, 1, (short) 4);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "Equipe jaune");
        item.setItemMeta(meta);
        yellow = item;
    }
    
}