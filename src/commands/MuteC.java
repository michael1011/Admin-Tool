package commands;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import main.PluginPrefix;
import main.main;

public class MuteC implements CommandExecutor {

	private main plugin;

	public MuteC(main main) {
		this.plugin = main;
		plugin.getCommand("mute").setExecutor(this);
	}
	
	public void save(){
			try {
			plugin.mutedP.save(main.mutedPf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		String NoPermission = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.NoPermission"));
		
		if(args.length == 0) {
			if(sender.hasPermission("admintool.mute")) {
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.Usage")));
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.MuteHelp1")));
				sender.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.MuteHelp2")));
			} else {
				sender.sendMessage(PluginPrefix.Prefix+NoPermission);
			}
			
		} else if(args.length == 1) {
			if(sender.hasPermission("admintool.mute")) {
				Player target = Bukkit.getPlayerExact(args[0]);
				
				if(target != null) {
					String targetN = target.getName();
					if(plugin.mutedP.getBoolean("Players."+targetN) != true) {
						plugin.mutedP.set("Players."+targetN, true);
						save();
						
						String MutedP = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.MutedP"));
						MutedP = MutedP.replaceAll("%target%", targetN);
						sender.sendMessage(PluginPrefix.Prefix+MutedP);
						
					} else if(plugin.mutedP.getBoolean("Players."+targetN) == true) {
						plugin.mutedP.set("Players."+targetN, false);
						save();
						
						String UnMutedP = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.UnMutedP"));
						UnMutedP = UnMutedP.replaceAll("%target%", targetN);
						sender.sendMessage(PluginPrefix.Prefix+UnMutedP);
					}
					
				} else {
					String mNotOn = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.MuteNotOnline"));
					mNotOn = mNotOn.replaceAll("%target%", args[0]);
					
					sender.sendMessage(PluginPrefix.Prefix+mNotOn);
				}
			} else {
				sender.sendMessage(PluginPrefix.Prefix+NoPermission);
			}
		}
		return true;
	}
}
