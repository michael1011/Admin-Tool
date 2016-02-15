package me.michael1011.main;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor {

	private main plugin;

	public Home(main main) {
		this.plugin = main;
		plugin.getCommand("home").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			Player p = (Player) sender;
			
			String NoPermission = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.NoPermission"));
			
			if (args.length == 0) {
				
				if (p.hasPermission("admintool.home")) {
					
					if (plugin.config.getBoolean("Homes." + p.getName() + ".enable") == true) {
						
						String[] parts = plugin.config.getString("Homes." + p.getName() + ".coords").split("/");
						Location player = new Location(Bukkit.getServer().getWorld(parts[3]), Integer.parseInt(parts[0]),
						         Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
						
						p.teleport(player);
						
					} else {
						p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.HomeHaventSet")));
						p.sendMessage(PluginPrefix.Prefix+"");
						p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
						p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.HomeHelp1")));
						p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.HomeHelp2")));
					}
				
				} else {
					p.sendMessage(PluginPrefix.Prefix+NoPermission);
				}
				
			} else if (args.length == 1) {
				
				Location location = p.getLocation();
				plugin.config.set("Homes." + p.getName() + ".coords", location.getBlockX() + "/" + location.getBlockY() + "/" +
						           location.getBlockZ() + "/" + location.getWorld().getName());
				plugin.config.set("Homes." + p.getName() + ".enable", true);
				try {
					plugin.config.save(main.configf);
				} catch (IOException e) {
					e.printStackTrace();
				};
				
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.HomeSet")));
				
			} else if (args.length > 1){
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.HomeHelp1")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.HomeHelp2")));
			}
			
		} else {
			Bukkit.getConsoleSender().sendMessage(PluginPrefix.Prefix+ChatColor.RED+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Console.OnlyPlayers")));
			}
		
		return true;
	}
}
