package listeners;

// Also the listener for GlobalMute!

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.ChatColor;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import main.PluginPrefix;
import main.main;

public class MuteL implements Listener {

	private main plugin;

	public MuteL(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if(plugin.config.getBoolean("GlobalMute")) {
			if(!e.getPlayer().hasPermission("admintool.globalmute.chat")) {
				e.setCancelled(true);
			}
		} else if(plugin.mutedP.getBoolean("Players."+e.getPlayer().getName())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.YouAreMuted")));
		}
	}
}
