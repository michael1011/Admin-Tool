package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import main.PluginPrefix;
import main.main;

public class Ping implements CommandExecutor {

	private main plugin;

	public Ping(main main) {
		this.plugin = main;
		plugin.getCommand("ping").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String NoPermission = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.NoPermission"));
			
		if(args.length == 0) {
			
			if (sender instanceof Player) {
				Player p = (Player) sender;
			
				if(p.hasPermission("admintool.ping")) {
					int ping = ((CraftPlayer) p).getHandle().ping;
					String pingP = Integer.toString(ping);
					String pingM = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Ping"));
					pingM = pingM.replace("%ping%", pingP);
						
					p.sendMessage(PluginPrefix.Prefix+pingM);
				
				} else {
					p.sendMessage(PluginPrefix.Prefix+NoPermission);
				}
				
			} else {
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.PingHelp2")));
			}
			
		} else if(args.length == 1) {
			Player target = Bukkit.getPlayerExact(args[0]);
			
			if(sender.hasPermission("admintool.ping.others")) {
				if(target != null) {
					
					if (target == sender) {
						Player p = (Player) sender;
						p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.PingSelf")));
					} else {
						int ping = ((CraftPlayer) target).getHandle().ping;
						String pingP = Integer.toString(ping);
						String pingM = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.PingPlayer"));
						pingM = pingM.replace("%ping%", pingP).replace("%target%", args[0]);
						
						sender.sendMessage(PluginPrefix.Prefix+pingM);
					}
					
				} else {
					String notFound = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.PingNotFound"));
					notFound = notFound.replace("%target%", args[0]);
					
					sender.sendMessage(PluginPrefix.Prefix+notFound);
				}
				
			} else {
				sender.sendMessage(PluginPrefix.Prefix+NoPermission);
			}
			
		} else {
			if (sender instanceof Player) {
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.PingHelp1")));
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.PingHelp2")));
			} else {
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.PingHelp2")));
			}
		}
		return true;
	}

}
