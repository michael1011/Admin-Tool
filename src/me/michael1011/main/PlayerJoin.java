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
					p.sendMessage("§eWelcome §6"+p.getName()+"§e!");
					
				}

			} else {
				
				if (plugin.config.getBoolean("JoinMessages.EnableWelcomePlayer") == true) {
					p.sendMessage("§eWelcome §6"+p.getName()+"§e!");
				}
				
				if (line2 != null) {
					p.sendMessage(line2);
				}

				if (line3 != null) {
					p.sendMessage(line3);
				}

				p.sendMessage("");
				
			}
		}
	}
}