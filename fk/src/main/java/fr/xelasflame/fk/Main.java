package fr.xelasflame.fk;

import org.bukkit.Bukkit;

import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin {




        @Override
        public void onEnable() {
            getLogger().info("Le plugin fk uhc a ete active !");
            Bukkit.getWorld("world").getWorldBorder().setSize(4096);
            getCommand("fk").setExecutor(new ManagerCommands());
            this.getServer().getPluginManager().registerEvents(new GameEvent(), this);
            ItemManager.init();
            BaseManager.loadcolor();
        }

        @Override
        public void onDisable() {
            getLogger().info("Le plugin fk uhc a ete desactive!");


        }
    }
