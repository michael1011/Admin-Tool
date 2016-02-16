package me.michael1011.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Invsee implements CommandExecutor {

	private main plugin;

	public Invsee(main main) {
		this.plugin = main;
		plugin.getCommand("invsee").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender inv_sender, Command inv_cmd, String inv_label, String[] inv_args) {
		
		String NoPermission = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.NoPermission"));
		
		if (inv_sender instanceof Player) {
			
			Player p = (Player) inv_sender;
		
			if (inv_args.length == 0) {
				
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.InvseeHelp1")));
				p.sendMessage(PluginPrefix.Prefix+"");
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.InvseeHelp2")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.InvseeHelp3")));
				
				
			} else if (inv_args.length == 1) {
				
				if (p.hasPermission("admintool.invsee")) {
					
					String name = inv_args[0];
					Player target = Bukkit.getPlayerExact(name);
					
					if (target == null) {

						p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.InvseeNoPlayer")));
					
					} else {
						
						if (target == p) {
							
							p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.InvseeOwn")));
						
						} else {
							p.openInventory(target.getInventory());
						}
					}
					
				} else {
					p.sendMessage(NoPermission);
				}
				
			} else {
				
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.InvseeHelp1")));
				p.sendMessage(PluginPrefix.Prefix+"");
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.InvseeHelp2")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.InvseeHelp3")));
				
			}
			
		} else {
			Bukkit.getConsoleSender().sendMessage(PluginPrefix.Prefix+ChatColor.RED+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Console.OnlyPlayers")));
		}
		
		return true;
	}

}
