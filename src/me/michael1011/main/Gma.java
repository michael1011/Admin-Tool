package me.michael1011.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Gma implements CommandExecutor {

	private main plugin;

	public Gma(main main) {
		this.plugin = main;
		plugin.getCommand("gma").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			String NoPermission = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Settings.NoPermissionMessage"));
			Boolean comp = false;
			
			Player p = (Player) sender;
			
			if (args.length == 0) {
				
				p.sendMessage(PluginPrefix.Prefix+"§4§lUsage:");
				p.sendMessage(PluginPrefix.Prefix+"§6/gma 1: §eset all players in the radius of GMARadius in the config to gamemode 1");
				p.sendMessage(PluginPrefix.Prefix+"§6/gm 1 500: §ecoming soon...");
				p.sendMessage(PluginPrefix.Prefix+"§eFor gamemode 0 replace the 1 with a 0.");
				
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
                                	
                    				p.sendMessage(PluginPrefix.Prefix+"§4§lUsage:");
                    				p.sendMessage(PluginPrefix.Prefix+"§6/gma 1: §eset all players in the radius of GMARadius in the config to gamemode 1");
                    				p.sendMessage(PluginPrefix.Prefix+"§6/gm 1 500: §ecoming soon...");
                    				p.sendMessage(PluginPrefix.Prefix+"§eFor gamemode 0 replace the 1 with a 0.");
                    				
                                }	
                    			
                    		} else {
                    			p.sendMessage(PluginPrefix.Prefix+"§eThere are §4no players §6in radius of " + range + " §e!");
                    			
                    		}
                    	}
                    }     
                       } else {
                    	   	p.sendMessage(PluginPrefix.Prefix+"§eThe radius §4shouldnt §ebe null sein!");
                    	   	p.sendMessage(PluginPrefix.Prefix+"§eChange in the §6config GMARADIUS§e!");
                			
                       }
					
					if (comp == true) {
						
						if (args[0].equals("1")) {
                            p.sendMessage(PluginPrefix.Prefix+"§eYou gave all players in the §6radius of " + range + " §6gamemode 1§e!");
						
						} else if (args[0].equals("0")) {
                            p.sendMessage(PluginPrefix.Prefix+"§eYou gave all players in the §6radius of " + range + " §6gamemode 0§e!");
						}
					}
                    
				} else {
					p.sendMessage(PluginPrefix.Prefix+NoPermission);
				}
				
				
			} else if (args.length == 2) {
				
				if (p.hasPermission("admintool.gma")) {
					
					p.sendMessage("§eCooming soon...");
					
				} else {
					p.sendMessage(NoPermission);
				}
			
			} else {
				
				p.sendMessage(PluginPrefix.Prefix+"§4§lUsage:");
				p.sendMessage(PluginPrefix.Prefix+"§6/gma 1: §eset all players in the radius of GMARadius in the config to gamemode 1");
				p.sendMessage(PluginPrefix.Prefix+"§6/gm 1 500: §ecoming soon...");
				p.sendMessage(PluginPrefix.Prefix+"§eFor gamemode 0 replace the 1 with a 0.");
			}
			
		} else {
			Bukkit.getConsoleSender().sendMessage(PluginPrefix.Prefix+ChatColor.RED+"Only players can execute this command!");
		}
		
		return true;
	}
}