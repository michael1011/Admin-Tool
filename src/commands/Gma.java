package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import main.PluginPrefix;
import main.main;

public class Gma implements CommandExecutor {

	private main plugin;

	public Gma(main main) {
		this.plugin = main;
		plugin.getCommand("gma").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			String NoPermission = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.NoPermission"));
			Boolean comp = false;
			
			Player p = (Player) sender;
			
			if (args.length == 0) {
				
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GMAHelp1")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GMAHelp2")));
				
			} else if (args.length == 1) {
				
				if (p.hasPermission("admintool.gma")) {
				
                    double range = plugin.getConfig().getDouble("Settings.GMARadius");
                    
					if (range != 0) {
                    
                    for (Entity e : p.getNearbyEntities(range,range,range)) {
                    	if (e instanceof Player) {
                    	
                    		Player p2 = (Player) e;
                    		String p2_c = p2.getServer().getName();
                    		
                    		if (p2_c != null) {

                        		if (args[0].equals("1")) {
                                    p2.setGameMode(GameMode.CREATIVE);
                                    
                        			comp = true;
                        			
                                } else if (args[0].equals("0")) {
                                    p2.setGameMode(GameMode.SURVIVAL);
                                    
                        			comp = true;
                        			
                                } else {
                                	
                    				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Player.Usage")));
                    				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GMAHelp1")));
                    				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GMAHelp2")));
                    				
                                }	
                    			
                    		} else {
                    			p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GMANoPlayer")));
                    			
                    		}
                    	}
                    }     
                       } else {
                    	   	p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GMAConfigNull1")));
                    	   	p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GMAConfigNull2")));
                			
                       }
					
					if (comp == true) {
						
						if (args[0].equals("1")) {
                            p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GMA1")));
						
						} else if (args[0].equals("0")) {
                            p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GMA0")));
						}
					}
                    
				} else {
					p.sendMessage(PluginPrefix.Prefix+NoPermission);
				}
			
			} else {
				
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Player.Usage")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GMAHelp1")));
				p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GMAHelp2")));
			}
			
		} else {
			Bukkit.getConsoleSender().sendMessage(PluginPrefix.Prefix+ChatColor.RED+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Console.OnlyPlayers")));Bukkit.getConsoleSender().sendMessage(PluginPrefix.Prefix+ChatColor.RED+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Console.OnlyPlayers")));
		}
		
		return true;
	}
}