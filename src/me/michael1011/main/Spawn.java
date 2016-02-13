package me.michael1011.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

	private main plugin;

	public Spawn(main main) {
		this.plugin = main;
		plugin.getCommand("spawn").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String NoPermission = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Settings.NoPermissionMessage"));
		
		if (sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if (args.length == 0) {
				
				if (plugin.getConfig().getBoolean("Settings.CustomSpawn") == false){
					
					if (p.hasPermission("admintool.spawn.set")) {
						p.sendMessage(PluginPrefix.Prefix+"§eYou have to set a spawn with §6/spawn set §e!");
					} else {
						p.sendMessage(PluginPrefix.Prefix+"§eThis command was §6disabled by a admin §efor all players!");
					}

				} else {
					
					if (p.hasPermission("admintool.spawn")) {
						String[] parts = plugin.getConfig().getString("Settings.spawnCoord").split("/");
						Location player = new Location(Bukkit.getServer().getWorld(parts[3]), Integer.parseInt(parts[0]),
						         Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
						
						p.teleport(player);
						p.sendMessage(PluginPrefix.Prefix+"§eNow you are at the §6spawn§e!");
					
					} else {
						p.sendMessage(PluginPrefix.Prefix+NoPermission);
					}
				}
				
			} else if (args.length == 1){
				
				if (args[0].equals("set")) {
					
					if (p.hasPermission("leben.spawn.set")) {
						
						Location location = p.getLocation();
						plugin.getConfig().set("Settings.spawnCoord", location.getBlockX() + "/" + location.getBlockY() + "/" +
						           location.getBlockZ() + "/" + location.getWorld().getName());
						plugin.getConfig().set("Settings.CustomSpawn", true);
						plugin.saveConfig();

						p.sendMessage(PluginPrefix.Prefix+"§eThe spawn was set §6successfully§e!");
						
					} else {
						p.sendMessage(NoPermission);
					}

				} else {
					p.sendMessage(PluginPrefix.Prefix+"§4§lUsage:");
					p.sendMessage(PluginPrefix.Prefix+"§6/spawn: §eteleports you to the spawn");
					p.sendMessage(PluginPrefix.Prefix+"§6/spawn set: §esets the spawn point");
				}
				
			} else {
				p.sendMessage(PluginPrefix.Prefix+"§4§lUsage:");
				p.sendMessage(PluginPrefix.Prefix+"§6/spawn: §eteleports you to the spawn");
				p.sendMessage(PluginPrefix.Prefix+"§6/spawn set: §esets the spawn point");
			}
				
				
		} else {
			Bukkit.getConsoleSender().sendMessage(PluginPrefix.Prefix+ChatColor.RED+"Only players can execute this command!");
		}
		
		return true;
	}

}
