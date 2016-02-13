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
		
		String NoPermission = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Settings.NoPermissionMessage"));
		
		if (inv_sender instanceof Player) {
			
			Player p = (Player) inv_sender;
		
			if (inv_args.length == 0) {
				
				p.sendMessage(PluginPrefix.Prefix+"§4§lUsage:");
				p.sendMessage(PluginPrefix.Prefix+"§6/invsee <player>: §esee the inventory of an other player");
				p.sendMessage(PluginPrefix.Prefix+"");
				p.sendMessage(PluginPrefix.Prefix+"§4§lExample:");
				p.sendMessage(PluginPrefix.Prefix+"§6/invsee Michi_amk : §eto see inventory of Michi_amk");
				
			} else if (inv_args.length == 1) {
				
				if (p.hasPermission("admintool.invsee")) {
					
					String name = inv_args[0];
					Player target = Bukkit.getPlayerExact(name);
					
					if (target == null) {

						p.sendMessage(PluginPrefix.Prefix+"§cThe player §4"+name+" §ccouldnt be found!");
					
					} else {
						
						if (target == p) {
							
							p.sendMessage(PluginPrefix.Prefix+"§cWhy do you want to see §4your own §cinventory?");
						
						} else {
							p.openInventory(target.getInventory());
						}
					}
					
				} else {
					p.sendMessage(NoPermission);
				}
				
			} else {
				
				p.sendMessage(PluginPrefix.Prefix+"§4§lUsage:");
				p.sendMessage(PluginPrefix.Prefix+"§6/invsee <player>: §esee the inventory of an other player");
				p.sendMessage(PluginPrefix.Prefix+"");
				p.sendMessage(PluginPrefix.Prefix+"§4§lExample:");
				p.sendMessage(PluginPrefix.Prefix+"§6/invsee Michi_amk : §eto see inventory of Michi_amk");
				
			}
			
		} else {
			Bukkit.getConsoleSender().sendMessage(PluginPrefix.Prefix+ChatColor.RED+"Only players can execute this command!");
		}
		
		return true;
	}

}
