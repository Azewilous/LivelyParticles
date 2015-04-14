package com.aze.livelyparticles;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * @author Azewilous
 */

public class LivelyParticles extends JavaPlugin implements Listener  {

public final Logger azelogs = Logger.getLogger("Minecraft");

	private static Plugin plugin;

    public void onEnable() {
    	PluginDescriptionFile pdfFile = this.getDescription();
        this.azelogs.info(pdfFile.getName() + " Version " + pdfFile.getVersion());
		  plugin = this;
		  
		  this.getCommand("lereload").setExecutor(new LivelyEffectsReload(this));
		  this.getCommand("livelyeffects").setExecutor(new LivelyEffects(this));
	        registerEvents(this, new LivelyEffects(this));
    }
    public void onDisable(){
    	PluginDescriptionFile pdfFile = this.getDescription();
        this.azelogs.info(pdfFile.getName() + " Version " + pdfFile.getVersion());
        plugin = null;
    }
    
    public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }
    
    public static Plugin getPlugin() {
        return plugin;
    }
}