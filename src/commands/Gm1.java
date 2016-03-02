package commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import main.PluginPrefix;
import main.main;

public class Gm1 implements CommandExecutor {
	
	private main plugin;

	public Gm1(main main) {
		this.plugin = main;
		plugin.getCommand("gm").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmnd, String label, String[] args) {
		
		String NoPermission = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.NoPermission"));
			
			if (args.length == 0 ) {
				
				if (sender instanceof Player) {
					Player p = (Player) sender;
					
					p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
					p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GM1Help")));
					p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GM0Help")));
					p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GMPHelp")));
				} else {
					sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));	
					sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GMPHelp")));
				}

			} else if (args.length == 1) {
				
				if(sender instanceof Player) {
					Player p = (Player) sender;
					
					if (args[0].equals("1")) {
						
						if(p.hasPermission("admintool.gm")) {
							p.setGameMode(GameMode.CREATIVE);
							p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GM1")));
						} else {
							p.sendMessage(PluginPrefix.Prefix+NoPermission);
						}
						
					} else if (args[0].equals("0")) {
						
						if(p.hasPermission("admintool.gm")) {
							p.setGameMode(GameMode.SURVIVAL);
							p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GM0")));
						} else {
							p.sendMessage(PluginPrefix.Prefix+NoPermission);
						}
						
					} else {
						p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
						p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GM1Help")));
						p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GM0Help")));
						p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GMPHelp")));
					}
					
				} else {
					sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));	
					sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GMPHelp")));
				}
					
			} else if(args.length == 2) {
				String gm = args[0];
				Player target = plugin.getServer().getPlayerExact(args[1]);
				
				if(sender.hasPermission("admintool.gm")) {
					if(target != null) {
						if(target == sender) {
							sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GMSelf")));
						} else {
							if(gm.equals("0")) {
								target.setGameMode(GameMode.SURVIVAL);
								String M0 = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GM0P"));
								M0 = M0.replaceAll("%target%", args[1]);
								
								sender.sendMessage(PluginPrefix.Prefix+M0);
							} else if(gm.equals("1")) {
								target.setGameMode(GameMode.CREATIVE);
								
								String M1 = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GM1P"));
								M1 = M1.replaceAll("%target%", args[1]);
								sender.sendMessage(PluginPrefix.Prefix+M1);
							}
						}
					} else {
						String nullM = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GMNotFound"));
						nullM = nullM.replaceAll("%player%", args[1]);
						
						sender.sendMessage(PluginPrefix.Prefix+nullM);
					}
				} else {
					sender.sendMessage(PluginPrefix.Prefix+NoPermission);
				}
				
			} else {
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GM1Help")));
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GM0Help")));
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GMPHelp")));
			}
		
		return true;
	}
}
