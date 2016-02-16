package me.michael1011.main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
	
	private main plugin;

	public PlayerJoin(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		String line2 = ChatColor.translateAlternateColorCodes('&', plugin.config.getString("JoinMessages.SecondLine"));
		String line3 = ChatColor.translateAlternateColorCodes('&', plugin.config.getString("JoinMessages.ThirdLine"));
	
		Player p = e.getPlayer();
		
		if (p.hasPlayedBefore()) {
			
			if(p.hasPermission("admintool.joinMessage")) {
				
				if (plugin.config.getBoolean("JoinMessages.EnableWelcomePlayer") == true) {
					
					String TagColor = ChatColor.translateAlternateColorCodes('&', plugin.config.getString("Settings.OPPrefix"));
					
					p.sendMessage(ChatColor.YELLOW+"Welcome "+TagColor+p.getName()+ChatColor.YELLOW+"!");
					
					if (plugin.updateAvailable && plugin.config.getBoolean("Settings.UpdaterSendPlayer")) {
						p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.UpdateMessage"))+plugin.version);
						p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.UpdateMessage2"))+"http://bit.ly/1QexebX");
					}
				}

			} else {
				
				if (plugin.config.getBoolean("JoinMessages.EnableWelcomePlayer") == true) {
					
					p.sendMessage(ChatColor.YELLOW+"Welcome "+ChatColor.GOLD+p.getName()+ChatColor.YELLOW+"!");
				}
				
				if (plugin.config.getBoolean("JoinMessages.SecondLineEnable")) {
					p.sendMessage(line2);
				}

				if (plugin.config.getBoolean("JoinMessages.ThirdLineEnable")) {
					p.sendMessage(line3);
				}

				p.sendMessage("");	
			}
		}
	}
}