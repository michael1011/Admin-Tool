package me.michael1011.main;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

public class MaintenanceC implements CommandExecutor {

	private main plugin;

	public MaintenanceC(main main) {
		this.plugin = main;
		plugin.getCommand("maintenance").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String NoPermission = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.NoPermission"));
		
		if (args.length == 0) {
			
			if (sender.hasPermission("admintool.maintenance.set")) {
				
				if (plugin.config.getBoolean("MaintenanceMode") == false) {
				
					plugin.config.set("MaintenanceMode", true);
					try {
						plugin.config.save(main.configf);
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						plugin.config.load(main.configf);
					} catch (IOException | InvalidConfigurationException e) {
						e.printStackTrace();
					}
					
					for (Player p : plugin.getServer().getOnlinePlayers()) {
			            if (!p.hasPermission("admintool.maintenance.join")) {
			                p.kickPlayer(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("MaintenanceMessage")));
			            }
					}
					
					sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.MaintenanceOn")));
					
				} else if (plugin.config.getBoolean("MaintenanceMode") == true ){
					
					plugin.config.set("MaintenanceMode", false);
					try {
						plugin.config.save(main.configf);
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						plugin.config.load(main.configf);
					} catch (IOException | InvalidConfigurationException e) {
						e.printStackTrace();
					}
					
					sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.MaintenanceOff")));
					
				} else {
					sender.sendMessage(PluginPrefix.Prefix+"Something went wrong...");
				}
				
			} else {
				sender.sendMessage(PluginPrefix.Prefix+NoPermission);
			}
			
		} else if (args.length == 1) {
			
			if (args[0].equalsIgnoreCase("on")) {
				
				if (sender.hasPermission("admintool.maintenance.set")) {
				
					plugin.config.set("MaintenanceMode", true);
					try {
						plugin.config.save(main.configf);
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						plugin.config.load(main.configf);
					} catch (IOException | InvalidConfigurationException e) {
						e.printStackTrace();
					}
					
					for (Player p : plugin.getServer().getOnlinePlayers()) {
			            if (!p.hasPermission("admintool.maintenance.join")) {
			                p.kickPlayer(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("MaintenanceMessage")));
			            }
					}
					
					sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.MaintenanceOn")));
				
				} else {
					sender.sendMessage(PluginPrefix.Prefix+NoPermission);
				}
				
			} else if (args[0].equalsIgnoreCase("off")) {
				
				if (sender.hasPermission("admintool.maintenance.set")) {
					
					plugin.config.set("MaintenanceMode", false);
					try {
						plugin.config.save(main.configf);
					} catch (IOException e){
						e.printStackTrace();
					}
					try {
						plugin.config.load(main.configf);
					} catch (IOException | InvalidConfigurationException e) {
						e.printStackTrace();
					}
				
					sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.MaintenanceOff")));
				}
			
			} else {
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.MaintenanceHelp1")));
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.MaintenanceHelp2")));
			}
			
		} else {
			sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
			sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.MaintenanceHelp1")));
			sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.MaintenanceHelp2")));
		}
		
		return true;
	}
}
