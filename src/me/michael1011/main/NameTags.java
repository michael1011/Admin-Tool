package me.michael1011.main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class NameTags implements Listener {

	private main plugin;

	public NameTags(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onNameTagRecieve(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		String name = player.getName();
		
		if (plugin.getConfig().getBoolean("Settings.OPPrefixEnable") == true) {

			String TagColor = plugin.getConfig().getString("Settings.OPColor");
			
			if (player.hasPermission("admintool.NameTag")) {
				player.setPlayerListName(TagColor + name);
				
				// include iTag?
			}
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent chat_event) {
	
		if (plugin.getConfig().getBoolean("Settings.OPPrefixEnable") == true) {
		
			Player chat_player = chat_event.getPlayer();
			String chat_name = chat_player.getName();
			String Msg = chat_event.getMessage();
			String AdminPrefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Settings.OPPrefix"));
				
				if (chat_player.hasPermission("admintool.NameTag")) {
					chat_event.setFormat(AdminPrefix + chat_name + "§f >> " + Msg);
				} else {
					chat_event.setFormat(chat_name + "§f >> " + Msg);
				
		}
	}
}
}