package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import main.PluginPrefix;
import main.main;

public class ClearChat implements CommandExecutor {

	private main plugin;

	public ClearChat(main main) {
		this.plugin = main;
		plugin.getCommand("clearchat").setExecutor(this);
	}

	public void MeChatClear(CommandSender sender) {
		for (int i = 0; i < 500 ; i++ ) {
			sender.sendMessage(" ");
			
			if (i == 499) {
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.ClearChat")));
			}
		}
	}
	
	public void GlobalChatClear(CommandSender sender) {
		sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.ClearChatGlobalA")));
		
		for(Player all : plugin.getServer().getOnlinePlayers()) {
			for (int a = 0; a < 500 ; a++) {
				
				if (!all.hasPermission("admintool.clearchat.global")) {
					all.sendMessage(" ");
				}
				if (a == 499) {
					if(!all.hasPermission("admintool.clearchat.global")) {
						all.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.ClearChatGlobal")));
					}
				}
			}
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lanel, String[] args) {

		String NoPermission = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.NoPermission"));
		
		
		if (args.length == 0) {
				
			if (sender.hasPermission("admintool.clearchat")) {
					
				MeChatClear(sender);	
				
			} else {
				sender.sendMessage(PluginPrefix.Prefix+NoPermission);
			}
			
		} else if (args.length == 1) {
			
			String name = args[0];
			Player target = Bukkit.getPlayerExact(name);
			
			if (args[0].equalsIgnoreCase("me")) {
				if (sender.hasPermission("admintool.clearchat")) {
					MeChatClear(sender);
				} else {
					sender.sendMessage(PluginPrefix.Prefix+NoPermission);
				}
			
			} else if (args[0].equalsIgnoreCase("global")) {
				if (sender.hasPermission("admintool.clearchat.global")) {
					GlobalChatClear(sender);
				} else {
					sender.sendMessage(PluginPrefix.Prefix+NoPermission);
				}
			
			} else if (target != null) {
				
				if (sender.hasPermission("admintool.clearchat.global")) {
					
					String sendname = target.getDisplayName();
					sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.ClearChatPlayer"))+sendname);
					MeChatClear(target);
					
				} else {
					sender.sendMessage(PluginPrefix.Prefix+NoPermission);
				}
				
			} else {
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.ClearChatHelp1")));
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.ClearChatHelp2")));
			}
			
		} else {
			
			sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
			sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.ClearChatHelp1")));
			sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.ClearChatHelp2")));
			
		}
		
		return true;
	}
}
