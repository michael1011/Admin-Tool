package me.michael1011.main;

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
			
			String NoPermission = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Settings.NoPermissionMessage"));
			
			if (args.length == 0) {
				
				if (p.hasPermission("admintool.home")) {
					
					if (plugin.getConfig().getBoolean("Homes." + p.getName() + ".enable") == true) {
						
						String[] parts = plugin.getConfig().getString("Homes." + p.getName() + ".coords").split("/");
						Location player = new Location(Bukkit.getServer().getWorld(parts[3]), Integer.parseInt(parts[0]),
						         Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
						
						p.teleport(player);
						
					} else {
						p.sendMessage(PluginPrefix.Prefix+"§eYou §4havent set §ea home yet!");
						p.sendMessage(PluginPrefix.Prefix+"");
						p.sendMessage(PluginPrefix.Prefix+"§4§lUsage:");
						p.sendMessage(PluginPrefix.Prefix+"§6/home: §eto teleport yourself to your home");
						p.sendMessage(PluginPrefix.Prefix+"§6/home set: §eset your home");
					}
				
				} else {
					p.sendMessage(PluginPrefix.Prefix+NoPermission);
				}
				
			} else if (args.length == 1) {
				
				Location location = p.getLocation();
				plugin.getConfig().set("Homes." + p.getName() + ".coords", location.getBlockX() + "/" + location.getBlockY() + "/" +
						           location.getBlockZ() + "/" + location.getWorld().getName());
				plugin.getConfig().set("Homes." + p.getName() + ".enable", true);
				plugin.saveConfig();
				
				p.sendMessage(PluginPrefix.Prefix+"§eYour home was set §6successfully§e!");
				
			} else if (args.length > 1){
				p.sendMessage(PluginPrefix.Prefix+"§4§lUsage:");
				p.sendMessage(PluginPrefix.Prefix+"§6/home: §eto teleport yourself to your home");
				p.sendMessage(PluginPrefix.Prefix+"§6/home set: §eset your home");
			}
			
		} else {
			Bukkit.getConsoleSender().sendMessage(PluginPrefix.Prefix+ChatColor.RED+"Only players can execute this command!");
			}
		
		return true;
	}
}
