package com.aze.livelyparticles;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/*
 * @author Azewilous
 */

public class LivelyEffects implements CommandExecutor, Listener {
	
	static HashMap<String, Boolean> storePlayers = new HashMap<String, Boolean>();
	static HashMap<String, Boolean> effectInUse = new HashMap<String, Boolean>();

	
LivelyParticles plugin;
	public LivelyEffects(LivelyParticles livelyParticles) {
		this.plugin = livelyParticles;
	}

	public void playEffect(Location location, Effect effect, boolean vert){
		for(int x = 0; x <= 8; x += ((!vert && (x == 3)) ?2 : 1))
			location.getWorld().playEffect(location, effect, x);
	}
	
	public static void stopEffect(Player splayer){
		storePlayers.remove(splayer.getName());
		effectInUse.remove(splayer.getName());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {
	if(sender instanceof Player){
		final Player player = (Player) sender;
	if(args.length == 0){
		if(player.hasPermission("le.help") || player.isOp()){
		player.sendMessage(ChatColor.DARK_AQUA + "==========LivelyParticles By Azewilous==========" 
				+ ChatColor.DARK_AQUA + "\n----------List Of Commands Commands" 
				+ ChatColor.DARK_AQUA + "\n----------/le play <effect>" 
				+ ChatColor.DARK_AQUA + "\n----------/le cease"
				+ ChatColor.DARK_AQUA + "\n----------/le list"
				+ "\n==========Created For The Uprising Server======");
		} else sender.sendMessage("Insufficient Permissions");
	}  else if(args.length == 1){
		if(args[0].equalsIgnoreCase("play")){
			if(player.hasPermission("le.play") || player.isOp()){
				player.sendMessage(ChatColor.YELLOW + "Please Specify An Effect"
					+ ChatColor.LIGHT_PURPLE + "\n/le play water"
					+ ChatColor.LIGHT_PURPLE + "\n/le play lava"
					+ ChatColor.LIGHT_PURPLE + "\n/le play smoke"
					+ ChatColor.LIGHT_PURPLE + "\n/le play star"
					+ ChatColor.LIGHT_PURPLE + "\n/le play explosion"
					+ ChatColor.LIGHT_PURPLE + "\n/le play cloud"
					+ ChatColor.LIGHT_PURPLE + "\n/le play heart"
					+ ChatColor.LIGHT_PURPLE + "\n/le play fireworks"
					+ ChatColor.LIGHT_PURPLE + "\n/le play note"
					+ ChatColor.LIGHT_PURPLE + "\n/le play splash"
					+ ChatColor.LIGHT_PURPLE + "\n/le play signal"
					+ ChatColor.LIGHT_PURPLE + "\n/le play flame"
					+ ChatColor.LIGHT_PURPLE + "\n/le play portal"
					+ ChatColor.LIGHT_PURPLE + "\n/le play tilebreak"
					+ ChatColor.LIGHT_PURPLE + "\n/le play dust"
					+ ChatColor.LIGHT_PURPLE + "\n/le play swirl");
			} else sender.sendMessage("Insufficient Permissions");
		} else 
			if(args[0].equalsIgnoreCase("cease")){
				if(player.hasPermission("le.cease") || player.isOp()){
					stopEffect(player);
					effectInUse.remove(player.getName());
					player.sendMessage(ChatColor.GREEN + "Canceling Particle Effects");
				return true;
				} else sender.sendMessage("Insufficient Permissions");
			} else
				if(args[0].equalsIgnoreCase("list")){
					if(player.hasPermission("le.list") || player.isOp()){
						player.sendMessage(ChatColor.GRAY + "Particle Effects"
								+ ChatColor.LIGHT_PURPLE + "\n/le play water"
								+ ChatColor.LIGHT_PURPLE + "\n/le play lava"
								+ ChatColor.LIGHT_PURPLE + "\n/le play smoke"
								+ ChatColor.LIGHT_PURPLE + "\n/le play star"
								+ ChatColor.LIGHT_PURPLE + "\n/le play explosion"
								+ ChatColor.LIGHT_PURPLE + "\n/le play cloud"
								+ ChatColor.LIGHT_PURPLE + "\n/le play heart"
								+ ChatColor.LIGHT_PURPLE + "\n/le play fireworks"
								+ ChatColor.LIGHT_PURPLE + "\n/le play note"
								+ ChatColor.LIGHT_PURPLE + "\n/le play splash"
								+ ChatColor.LIGHT_PURPLE + "\n/le play signal"
								+ ChatColor.LIGHT_PURPLE + "\n/le play flame"
								+ ChatColor.LIGHT_PURPLE + "\n/le play portal"
								+ ChatColor.LIGHT_PURPLE + "\n/le play tilebreak"
								+ ChatColor.LIGHT_PURPLE + "\n/le play dust"
								+ ChatColor.LIGHT_PURPLE + "\n/le play swirl");
					}
				}
		} else if(args.length == 2) {
			if(args[1].equalsIgnoreCase("water")){
				if(player.hasPermission("le.play.water") || player.isOp()){
					if(!effectInUse.containsKey(player.getName())) {
				storePlayers.put(player.getName(), true);
    			  player.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
    				@Override
    				public void run() {
    					if(storePlayers.containsKey(player.getName())){
							playEffect(player.getLocation().add(0, 1, 0), Effect.WATERDRIP, false);	
							playEffect(player.getLocation().add(-1, 1, 0), Effect.WATERDRIP, false);
							playEffect(player.getLocation().add(0, 1, 1), Effect.WATERDRIP, false);	
    					}
    				}
    			}, 0, 0);
    			   	return true;
					} else stopEffect(player);
						player.sendMessage(ChatColor.RED + "Canceling Current Effect");
				} else sender.sendMessage(ChatColor.RED + "Insufficient Permissions");
			} else if(args[1].equalsIgnoreCase("lava")){
				if(player.hasPermission("le.play.lava") || player.isOp()){
					if(!effectInUse.containsKey(player.getName())) {
				storePlayers.put(player.getName(), true);
				effectInUse.put(player.getName(), true);
				player.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
					@Override
    				public void run() {
						if(storePlayers.containsKey(player.getName())){
							playEffect(player.getLocation().add(0, 1, 0), Effect.LAVADRIP, false);
							playEffect(player.getLocation().add(-1, 1, 0), Effect.LAVADRIP, false);
							playEffect(player.getLocation().add(0, 1, 1), Effect.LAVADRIP, false);
						}
					}
    			}, 0, 0);
    				return true;
					} else stopEffect(player);
					player.sendMessage(ChatColor.RED + "Canceling Current Effect");
				} else sender.sendMessage(ChatColor.RED + "Insufficient Permissions");
			} else if(args[1].equalsIgnoreCase("smoke")){
				if(player.hasPermission("le.play.smoke") || player.isOp()){
					if(!effectInUse.containsKey(player.getName())) {
				storePlayers.put(player.getName(), true);
				effectInUse.put(player.getName(), true);
				player.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
					@Override
    				public void run() {
						if(storePlayers.containsKey(player.getName())){
							playEffect(player.getLocation().add(0, 1, 0), Effect.SMALL_SMOKE, false);
							playEffect(player.getLocation().add(-1, 1, 0), Effect.SMALL_SMOKE, false);
							playEffect(player.getLocation().add(0, 1, 1), Effect.SMALL_SMOKE, false);
						}
					}
    			}, 0, 0);
    				return true;
					} else stopEffect(player);
					player.sendMessage(ChatColor.RED + "Canceling Current Effect");
				} else sender.sendMessage(ChatColor.RED + "Insufficient Permissions");
			} else if(args[1].equalsIgnoreCase("star")){
				if(player.hasPermission("le.play.star") || player.isOp()){
					if(!effectInUse.containsKey(player.getName())) {
				storePlayers.put(player.getName(), true);
				effectInUse.put(player.getName(), true);
				player.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
					@Override
    				public void run() {
						if(storePlayers.containsKey(player.getName())){
							playEffect(player.getLocation().add(0, 1, 0), Effect.MAGIC_CRIT, false);
						}
					}
    			}, 0, 0);
    				return true;
					} else stopEffect(player);
					player.sendMessage(ChatColor.RED + "Canceling Current Effect");
				}else sender.sendMessage(ChatColor.RED + "Insufficient Permissions");
			} else if(args[1].equalsIgnoreCase("explosion")){
				if(player.hasPermission("le.play.explosion") || player.isOp()){
					if(!effectInUse.containsKey(player.getName())) {
				storePlayers.put(player.getName(), true);
				effectInUse.put(player.getName(), true);
					player.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
						@Override
						public void run(){
							if(storePlayers.containsKey(player.getName())){
								playEffect(player.getLocation(), Effect.EXPLOSION, false);
							}
						}
					}, 0, 0);
					return true;
					} else stopEffect(player);
					player.sendMessage(ChatColor.RED + "Canceling Current Effect");
				} else sender.sendMessage(ChatColor.RED + "Insufficient Permissions");
			} else if(args[1].equalsIgnoreCase("cloud")){
				if(player.hasPermission("le.play.cloud") || player.isOp()){
					if(!effectInUse.containsKey(player.getName())) {
					storePlayers.put(player.getName(), true);
					effectInUse.put(player.getName(), true);
					player.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
						@Override
						public void run(){
							if(storePlayers.containsKey(player.getName())){
								playEffect(player.getLocation().add(0, 2, 0), Effect.VILLAGER_THUNDERCLOUD, false);
							}
						}
					}, 0, 5);
					return true;
					} else stopEffect(player);
					player.sendMessage(ChatColor.RED + "Canceling Current Effect");
				} else sender.sendMessage(ChatColor.RED + "Insufficient Permissions");
			} else if(args[1].equalsIgnoreCase("heart")){
				if(player.hasPermission("le.play.heart") || player.isOp()){
					if(!effectInUse.containsKey(player.getName())) {
				storePlayers.put(player.getName(), true);
				effectInUse.put(player.getName(), true);
					player.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
						@Override
						public void run(){
							if(storePlayers.containsKey(player.getName())){
								playEffect(player.getLocation().add(0, 2, 0), Effect.HEART, false);
							}
						}
					}, 0, 5);
					return true;
					} else stopEffect(player);
					player.sendMessage(ChatColor.RED + "Canceling Current Effect");
				} else sender.sendMessage(ChatColor.RED + "Insufficient Permissions");
			} else if(args[1].equalsIgnoreCase("fireworks")){
				if(player.hasPermission("le.play.fireworks") || player.isOp()){
					if(!effectInUse.containsKey(player.getName())) {
				storePlayers.put(player.getName(), true);
				effectInUse.put(player.getName(), true);
					player.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
						@Override
						public void run(){
							if(storePlayers.containsKey(player.getName())){
								playEffect(player.getLocation(), Effect.FIREWORKS_SPARK, false);
							}
						}
					}, 0, 0);
					return true;
					} else stopEffect(player);
					player.sendMessage(ChatColor.RED + "Canceling Current Effect");
				}else sender.sendMessage(ChatColor.RED + "Insufficient Permissions");
			} else if(args[1].equalsIgnoreCase("note")){
				if(player.hasPermission("le.play.note") || player.isOp()){
					if(!effectInUse.containsKey(player.getName())) {
				storePlayers.put(player.getName(), true);
				effectInUse.put(player.getName(), true);
					player.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
						@Override
						public void run(){
							if(storePlayers.containsKey(player.getName())){
								playEffect(player.getLocation().add(0, 2, 0), Effect.NOTE, false);
								playEffect(player.getLocation().add(-1, 2, 0), Effect.NOTE, false);
								playEffect(player.getLocation().add(0, 2, 1), Effect.NOTE, false);
							}
						}
					}, 0, 4);
					return true;
					} else stopEffect(player);
					player.sendMessage(ChatColor.RED + "Canceling Current Effect");
				} else sender.sendMessage(ChatColor.RED + "Insufficient Permissions");
			} else if(args[1].equalsIgnoreCase("splash")){
				if(player.hasPermission("le.play.splash") || player.isOp()){
					if(!effectInUse.containsKey(player.getName())) {
				storePlayers.put(player.getName(), true);
				effectInUse.put(player.getName(), true);
					player.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
						@Override
						public void run(){
							if(storePlayers.containsKey(player.getName())){
								playEffect(player.getLocation().add(0, 2, 0), Effect.SPLASH, false);
							}
						}
					}, 0, 2);
					return true;
					} else stopEffect(player);
					player.sendMessage(ChatColor.RED + "Canceling Current Effect");
				} else sender.sendMessage(ChatColor.RED + "Insufficient Permissions");
			} else if(args[1].equalsIgnoreCase("signal")){
				if(player.hasPermission("le.play.signal") || player.isOp()){
					if(!effectInUse.containsKey(player.getName())) {
				storePlayers.put(player.getName(), true);
				effectInUse.put(player.getName(), true);
					player.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
						@Override
						public void run(){
							if(storePlayers.containsKey(player.getName())){
								playEffect(player.getLocation().add(0, 2, 0), Effect.ENDER_SIGNAL, false);
							}
						}
					}, 0, 2);
					return true;
					} else stopEffect(player);
					player.sendMessage(ChatColor.RED + "Canceling Current Effect");
				} else sender.sendMessage(ChatColor.RED + "Insufficient Permissions");
			} else if(args[1].equalsIgnoreCase("flame")){
				if(player.hasPermission("le.play.flame") || player.isOp()){
					if(!effectInUse.containsKey(player.getName())) {
				storePlayers.put(player.getName(), true);
				effectInUse.put(player.getName(), true);
				player.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
					@Override
					public void run(){
						if(storePlayers.containsKey(player.getName())){
							playEffect(player.getLocation().add(0, 2, 0), Effect.FLAME, false);
						}
					}
				}, 0, 2);
					return true;
					} else stopEffect(player);
					player.sendMessage(ChatColor.RED + "Canceling Current Effect");
				} else sender.sendMessage(ChatColor.RED + "Insufficient Permissions");
			} else if(args[1].equalsIgnoreCase("portal")){
				if(player.hasPermission("le.play.portal") || player.isOp()){
					if(!effectInUse.containsKey(player.getName())) {
					storePlayers.put(player.getName(), true);
					effectInUse.put(player.getName(), true);
						player.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
							@Override
							public void run(){
								if(storePlayers.containsKey(player.getName())){
									playEffect(player.getLocation().add(0, 2, 0), Effect.PORTAL, false);
								}
							}
						}, 0, 0);
							return true;
					} else stopEffect(player);
					player.sendMessage(ChatColor.RED + "Canceling Current Effect");
					} else sender.sendMessage(ChatColor.RED + "Insufficient Permissions");
				} else if(args[1].equalsIgnoreCase("tilebreak")){
					if(player.hasPermission("le.play.tilebreak") || player.isOp()){
						if(!effectInUse.containsKey(player.getName())) {
					storePlayers.put(player.getName(), true);
					effectInUse.put(player.getName(), true);
						player.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
							@Override
							public void run(){
								if(storePlayers.containsKey(player.getName())){
									playEffect(player.getLocation().add(0, 0, 0), Effect.TILE_BREAK, false);
								}
							}
						}, 0, 2);
						return true;
						} else stopEffect(player);
						player.sendMessage(ChatColor.RED + "Canceling Current Effect");
					} else sender.sendMessage(ChatColor.RED + "Insufficient Permissions");
				} else if(args[1].equalsIgnoreCase("dust")){
					if(player.hasPermission("le.play.dust") || player.isOp()){
						if(!effectInUse.containsKey(player.getName())) {
					storePlayers.put(player.getName(), true);
					effectInUse.put(player.getName(), true);
						player.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
							@Override
							public void run(){
								if(storePlayers.containsKey(player.getName())){
									playEffect(player.getLocation().add(0, 1, 0), Effect.COLOURED_DUST, false);
									playEffect(player.getLocation().add(-1, 1, 0), Effect.COLOURED_DUST, false);
									playEffect(player.getLocation().add(0, 1, 1), Effect.COLOURED_DUST, false);
								}
							}
						}, 0, 1);
						return true;
						} else stopEffect(player);
						player.sendMessage(ChatColor.RED + "Canceling Current Effect");
					} else sender.sendMessage(ChatColor.RED + "Insufficient Permissions");
				} else if(args[1].equalsIgnoreCase("swirl")){
					if(player.hasPermission("le.play.swirl") || player.isOp()){
						if(!effectInUse.containsKey(player.getName())) {
					storePlayers.put(player.getName(), true);
					effectInUse.put(player.getName(), true);
						player.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
							@Override
							public void run(){
								if(storePlayers.containsKey(player.getName())){
									playEffect(player.getLocation().add(0, 1, 0), Effect.POTION_SWIRL_TRANSPARENT, false);
								}
							}
						}, 0, 2);
						return true;
						} else stopEffect(player);
						player.sendMessage(ChatColor.RED + "Canceling Current Effect");
					} else sender.sendMessage("Insufficient Permissions");
				}
		}  
		return true;
	} else 
		sender.sendMessage("Must Be A Player");
	return true;
	}
	
	@EventHandler
	public void onLogout(PlayerQuitEvent event){
		Player player = (Player) event.getPlayer();
		if(storePlayers.containsKey(player)){
			stopEffect(player);
		} else
			return;
	}
}
