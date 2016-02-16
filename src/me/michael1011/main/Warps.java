package me.michael1011.main;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warps implements CommandExecutor {

	private main plugin;

	public Warps(main main) {
		this.plugin = main;
		plugin.getCommand("warp").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			String NoPermision = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.NoPermission"));
			
			Player p = (Player) sender;
			
			if (args.length == 0) {
				
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.WarpHelp1")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.WarpHelp2")));
				
			} else if (args.length == 1) {
				
				String arg1 = args[0].toString();
				
				if (plugin.warps.getString("Warps."+arg1+".coords") != null && plugin.warps.getBoolean("Warps."+arg1+".enable") != false) {
					
					if (p.hasPermission("admintool.warp."+arg1)) {
						
						String[] parts = plugin.warps.getString("Warps." + arg1 + ".coords").split("/");
						Location player = new Location(Bukkit.getServer().getWorld(parts[5]), Integer.parseInt(parts[0]),
						         Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Float.parseFloat(parts[3]), Float.parseFloat(parts[4]));
						
						p.teleport(player);
						
					} else {
						p.sendMessage(PluginPrefix.Prefix+NoPermision);
					}
					
				} else {
					p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.WarpDoesNotExist")));
				}
				
			} else if (args.length == 2) {

				String arg1 = args[1].toString();
				
				if (args[0].equalsIgnoreCase("create")) {
					
					if (p.hasPermission("admintool.warp.create")) {
						
						if (plugin.warps.getString("Warps."+arg1+".enable") == null) {
							p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.WarpCreated")));
						
						} else {
							p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.WarpUpdated")));
						}
						
						Location location = p.getLocation();
						plugin.warps.set("Warps." + arg1 + ".coords", location.getBlockX() + "/" + location.getBlockY() + "/" +
								           location.getBlockZ() + "/" + location.getYaw() + "/" + location.getPitch() + "/" + location.getWorld().getName());
						plugin.warps.set("Warps." + arg1 + ".enable", true);
						
						try {
							plugin.warps.save(main.warpsf);
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					} else {
						p.sendMessage(PluginPrefix.Prefix+NoPermision);
					}
					
				} else {
					p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
					p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.WarpHelp1")));
					p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.WarpHelp2")));
				}
				
			} else {
			
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.WarpHelp1")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.WarpHelp2")));
				
			}
			
		} else {
			Bukkit.getConsoleSender().sendMessage(PluginPrefix.Prefix+ChatColor.RED+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Console.OnlyPlayers")));
		}
		
		return true;
	}

}
