package me.michael1011.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Day implements CommandExecutor {

	private main plugin;

	public Day(main main) {
		this.plugin = main;
		plugin.getCommand("day").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender day_sender, Command day_cmd, String day_label, String[] day_args) {
		
		String NoPermission = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Settings.NoPermissionMessage"));
		
		if (day_sender instanceof Player) {
			
			Player p = (Player) day_sender;
			
			if (day_args.length == 0 ) {
				
				if (p.hasPermission("admintool.day")){
					p.getWorld().setTime(1000);
					p.sendMessage(PluginPrefix.Prefix+"§eSet time to §6day§e!");
					
				} else {
					p.sendMessage(NoPermission);
				}
				
			} else {
				p.sendMessage(PluginPrefix.Prefix+"§4§lUsage:");
				p.sendMessage(PluginPrefix.Prefix+"§6/day: §eset the time in your map to day");
			}
			
		} else {
			Bukkit.getConsoleSender().sendMessage(PluginPrefix.Prefix+ChatColor.RED+"Only players can execute this command!");
		}
		return true;
	}
}
