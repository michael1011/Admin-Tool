package me.michael1011.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gm1 implements CommandExecutor {
	
	private main plugin;

	public Gm1(main main) {
		this.plugin = main;
		plugin.getCommand("gm").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmnd, String label, String[] args) {
		
		String NoPermission = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.NoPermission"));
		
		if (sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if (args.length == 0 ) {
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GM1Help")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GM0Help")));
				
			} else if (args.length == 1) {
				
				if (args[0].equals("1")) {
					
					if(p.hasPermission("admintool.gm")) {
						p.setGameMode(GameMode.CREATIVE);
						p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GM1")));
					} else {
						p.sendMessage(PluginPrefix.Prefix+NoPermission);
					}
					
				} else if (args[0].equals("0")) {
					
					if(p.hasPermission("admintool.gm")) {
						p.setGameMode(GameMode.SURVIVAL);
						p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GM0")));
					} else {
						p.sendMessage(PluginPrefix.Prefix+NoPermission);
					}
					
				} else {
					p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
					p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GM1Help")));
					p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GM0Help")));
				}
				
			} else {
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GM1Help")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GM0Help")));
			}
			
		} else {
			Bukkit.getConsoleSender().sendMessage(PluginPrefix.Prefix+ChatColor.RED+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Console.OnlyPlayers")));
		}
		
		return true;
	}
}
