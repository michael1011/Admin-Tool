package me.michael1011.main;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

	private main plugin;

	public Spawn(main main) {
		this.plugin = main;
		plugin.getCommand("spawn").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String NoPermission = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.NoPermission"));
		
		if (sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if (args.length == 0) {
				
				if (plugin.config.getBoolean("Settings.CustomSpawn") == false){
					
					if (p.hasPermission("admintool.spawn.set")) {
						p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.SpawnNotSet")));
					} else {
						p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.SpawnNotSetP")));
					}

				} else {
					
					if (p.hasPermission("admintool.spawn")) {
						String[] parts = plugin.config.getString("Settings.spawnCoord").split("/");
						Location player = new Location(Bukkit.getServer().getWorld(parts[3]), Integer.parseInt(parts[0]),
						         Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
						
						p.teleport(player);
						p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Spawn")));
					
					} else {
						p.sendMessage(PluginPrefix.Prefix+NoPermission);
					}
				}
				
			} else if (args.length == 1){
				
				if (args[0].equals("set")) {
					
					if (p.hasPermission("leben.spawn.set")) {
						
						Location location = p.getLocation();
						plugin.config.set("Settings.spawnCoord", location.getBlockX() + "/" + location.getBlockY() + "/" +
						           location.getBlockZ() + "/" + location.getWorld().getName());
						plugin.config.set("Settings.CustomSpawn", true);
						try {
							plugin.config.save(main.configf);
						} catch (IOException e) {
							e.printStackTrace();
						};

						p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.SpawnSet")));
						
					} else {
						p.sendMessage(NoPermission);
					}

				} else {
					p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
					p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.SpawnHelp1")));
					p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.SpawnHelp2")));
				}
				
			} else {
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.SpawnHelp1")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.SpawnHelp2")));
			}
				
				
		} else {
			Bukkit.getConsoleSender().sendMessage(PluginPrefix.Prefix+ChatColor.RED+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Console.OnlyPlayers")));
		}
		
		return true;
	}

}
