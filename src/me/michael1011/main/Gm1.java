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
		
		String NoPermission = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Settings.NoPermissionMessage"));
		
		if (sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if (args.length == 0 ) {
				p.sendMessage(PluginPrefix.Prefix+"§4§lUsage:");
				p.sendMessage(PluginPrefix.Prefix+"§6/gm 1: §efor gamemode 1");
				p.sendMessage(PluginPrefix.Prefix+"§6/gm 0: §efor gamemode 0");
				
			} else if (args.length == 1) {
				
				if (args[0].equals("1")) {
					
					if(p.hasPermission("admintool.gm")) {
						p.setGameMode(GameMode.CREATIVE);
						p.sendMessage(PluginPrefix.Prefix+"§eYou are in §6gamemode 1§e now!");
					} else {
						p.sendMessage(PluginPrefix.Prefix+NoPermission);
					}
					
				} else if (args[0].equals("0")) {
					
					if(p.hasPermission("admintool.gm")) {
						p.setGameMode(GameMode.SURVIVAL);
						p.sendMessage(PluginPrefix.Prefix+"§eDu bist nun im §6Gamemode 0§e!");
					} else {
						p.sendMessage(PluginPrefix.Prefix+NoPermission);
					}
					
				} else {
					p.sendMessage(PluginPrefix.Prefix+"§4§lUsage:");
					p.sendMessage(PluginPrefix.Prefix+"§6/gm 1: §efor gamemode 1");
					p.sendMessage(PluginPrefix.Prefix+"§6/gm 0: §efor gamemode 0");
				}
				
			} else {
				p.sendMessage(PluginPrefix.Prefix+"§4§lUsage:");
				p.sendMessage(PluginPrefix.Prefix+"§6/gm 1: §efor gamemode 1");
				p.sendMessage(PluginPrefix.Prefix+"§6/gm 0: §efor gamemode 0");
			}
			
		} else {
			Bukkit.getConsoleSender().sendMessage(PluginPrefix.Prefix+ChatColor.RED+"Only players can execute this command!");
		}
		
		return true;
	}
}
