package com.aze.livelyparticles;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/*
 * @author Azewilous
 */

public class LivelyEffectsReload implements CommandExecutor {

LivelyParticles plugin;

	public LivelyEffectsReload(LivelyParticles livelyParticles) {
		this.plugin = livelyParticles;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel,
			String[] args) {
		
		if(args.length == 0){
			if(sender.hasPermission("le.reload") || sender.isOp()){
				Bukkit.getPluginManager().disablePlugin(plugin);
				Bukkit.getPluginManager().enablePlugin(plugin);
				sender.sendMessage(ChatColor.YELLOW + "The Plugin " + ChatColor.LIGHT_PURPLE + plugin + ChatColor.YELLOW + " Has Been Reloaded!");
			} else
				sender.sendMessage("Insufficient Permissions");
		}
		return true;
	}

}
