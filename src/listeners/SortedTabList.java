package listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import main.main;

public class SortedTabList implements Listener {

	private main plugin;

	public SortedTabList(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	public void setPrefix(Player p) {
		
		String team;
		
		if(p.hasPermission("admintool.NameTag")) {
			team = "00Admin";
		} else {
			team = "01Player";
		}
		
		String player = p.getName();
		plugin.sb.getTeam(team).addEntry(player);
		p.setDisplayName(plugin.sb.getTeam(team).getPrefix() + p.getName());
		
		for (Player a : Bukkit.getOnlinePlayers()) {
			a.setScoreboard(plugin.sb);
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		setPrefix(e.getPlayer());
	}
}
