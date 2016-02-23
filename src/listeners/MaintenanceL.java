package listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.server.ServerListPingEvent;

import main.main;

public class MaintenanceL implements Listener {

	private main plugin;

	public MaintenanceL(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		
		if (plugin.config.getBoolean("MaintenanceMode")) {
			
			if (!event.getPlayer().hasPermission("admintool.maintenance.join")) {
				event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.translateAlternateColorCodes('&', plugin.config.getString("MaintenanceMessage")));
			}
		}
	}

	@EventHandler
	public void onPacketSending(ServerListPingEvent event) {
		if (plugin.config.getBoolean("MaintenanceMode")) {
			
			event.setMaxPlayers(0);
            
			if (plugin.config.getBoolean("MaintenanceMotd")) {
				
				String motd = plugin.config.getString("MaintenanceMotdText");
				
				event.setMotd(ChatColor.translateAlternateColorCodes('&', motd));
			}

		}
	}
}
