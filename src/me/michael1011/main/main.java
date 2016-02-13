package me.michael1011.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


// Todo: PingEvent, Ping, BagPack, fix gma, custom book,

public class main extends JavaPlugin implements CommandExecutor {
	
	@Override
	public void onEnable() {
		cfg();
		
		new Home(this);
		new Spawn(this);
		new Gma(this);
		new NameTags(this);
		new Invsee(this);
		new Day(this);
		new Gm1(this);
	    new PlayerJoin(this);
	    
		Bukkit.getConsoleSender().sendMessage(PluginPrefix.Prefix+ChatColor.RED+"Plugin enabled!");
	}
	
	public void cfg(){
		reloadConfig();
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(PluginPrefix.Prefix+ChatColor.RED+"Plugin disabled!");
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmnd, String label, String[] args) {

		String NoPermission = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Settings.NoPermissionMessage"));
		
		if (sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if (args.length == 0) {
			
				if (p.hasPermission("admintool.reload")) {
					reloadConfig();
					p.sendMessage(PluginPrefix.Prefix+"§eReloaded Admin Tool config §6successfully§e!");
				} else {
					p.sendMessage(PluginPrefix.Prefix+NoPermission);
				}
				
			} else {
				p.sendMessage(PluginPrefix.Prefix+"§4§lUsage:");
				p.sendMessage(PluginPrefix.Prefix+"§6/adminreload: §ereload the Admin Tool config");
			}
			
		} else {
			reloadConfig();
			Bukkit.getConsoleSender().sendMessage(PluginPrefix.Prefix+ChatColor.YELLOW+"Reloaded Admin Tool config "+ChatColor.GOLD+"successfully"+ChatColor.YELLOW+"!");
		}

		return true;
	}
}
