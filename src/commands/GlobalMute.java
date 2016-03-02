package commands;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import main.PluginPrefix;
import main.main;
import net.md_5.bungee.api.ChatColor;

public class GlobalMute implements CommandExecutor {

	private main plugin;

	public GlobalMute(main main) {
		this.plugin = main;
		plugin.getCommand("globalmute").setExecutor(this);
	}
	
	public void save(){
		try {
		plugin.config.save(main.configf);
	} catch (IOException e) {
		e.printStackTrace();
	}
}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Boolean comp = plugin.config.getBoolean("GlobalMute");
		
		if(sender.hasPermission("admintool.globalmute")) {
			if(args.length == 0) {
				
				if(comp) {
					plugin.config.set("GlobalMute", false);
					save();
					
					sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GlobalMuteOff")));
				} else {
					plugin.config.set("GlobalMute", true);
					save();
					
					sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GlobalMuteOn")));
					plugin.getServer().broadcastMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GlubalMuteBroad")));
				}
				
			} else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("off")) {
					if(comp) {
						plugin.config.set("GlobalMute", false);
						save();
						
						sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GlobalMuteOff")));
					} else {
						sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GlobalMuteAlrOff")));
					}
				} else if(args[0].equalsIgnoreCase("on")) {
					if(comp) {
						sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GlobalMuteAlrOn")));
					} else {
						plugin.config.set("GlobalMute", true);
						save();
						
						sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GlobalMuteOn")));
						plugin.getServer().broadcastMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GlobalMuteBroad")));
					}
				} else {
					sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
					sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GlobalMuteHelp1")));
					sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GlobalMuteHelp2")));
				}
				
			} else {
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GlobalMuteHelp1")));
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GlobalMuteHelp2")));
			}
			
		} else {
			sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.NoPermission")));
		}
		
		return true;
	}

}
