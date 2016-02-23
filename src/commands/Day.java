package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import main.PluginPrefix;
import main.main;

public class Day implements CommandExecutor {

	private main plugin;

	public Day(main main) {
		this.plugin = main;
		plugin.getCommand("day").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender day_sender, Command day_cmd, String day_label, String[] day_args) {
		
		String NoPermission = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.NoPermission"));
		
		if (day_sender instanceof Player) {
			
			Player p = (Player) day_sender;
			
			if (day_args.length == 0 ) {
				
				if (p.hasPermission("admintool.day")){
					p.getWorld().setTime(1000);
					p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Day")));
					
				} else {
					p.sendMessage(NoPermission);
				}
				
			} else {
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.DayHelp")));
			}
			
		} else {
			Bukkit.getConsoleSender().sendMessage(PluginPrefix.Prefix+ChatColor.RED+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Console.OnlyPlayers")));
		}
		return true;
	}
}
