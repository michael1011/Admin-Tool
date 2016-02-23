package listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import main.main;

public class NameTags implements Listener {

	private main plugin;

	public NameTags(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent chat_event) {
	
		if (plugin.getConfig().getBoolean("Settings.OPPrefixEnable") == true) {
		
			Player chat_player = chat_event.getPlayer();
			String chat_name = chat_player.getName();
			String Msg = chat_event.getMessage();
			String AdminPrefix = ChatColor.translateAlternateColorCodes('&', plugin.config.getString("Settings.OPPrefix"));
				
				if (chat_player.hasPermission("admintool.NameTag")) {
					chat_event.setFormat(AdminPrefix + chat_name + ChatColor.WHITE + " >> " + Msg);
				} else {
					chat_event.setFormat(chat_name + ChatColor.WHITE + " >> " + Msg);
				
		}
	}
}
}