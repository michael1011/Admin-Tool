package GUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import main.PluginPrefix;
import main.main;

public class GUIOpener implements CommandExecutor {
	
	private main plugin;

	public GUIOpener(main main) {
		this.plugin = main;
		plugin.getCommand("admingui").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String NoPermission = ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.NoPermission"));
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if (p.hasPermission("admintool.gui")) {
				
				if(args.length == 0) {
					p.openInventory(main.GUI);
				} else if(args.length == 1) {
					
					if(args[0].equalsIgnoreCase("home")) {
						main.home = p.getServer().createInventory(null, 9, "§8§lHome");
						
						ItemStack backI = new ItemStack(Material.ARROW);
						ItemMeta backM = backI.getItemMeta();
						backM.setDisplayName("§7Go back");
						backI.setItemMeta(backM);
						
						ItemStack homeI = new ItemStack(Material.BED, 1);
						ItemMeta homeM = homeI.getItemMeta();
						homeM.setDisplayName("§eTeleport you §6home");
						homeI.setItemMeta(homeM);
						
						ItemStack sethomeI = new ItemStack(Material.COMPASS);
						ItemMeta sethomeM = sethomeI.getItemMeta();
						sethomeM.setDisplayName("§eSet your §6home §ehere");
						sethomeI.setItemMeta(sethomeM);
						
						main.home.setItem(0, backI);
						main.home.setItem(3, homeI);
						main.home.setItem(6, sethomeI);
						p.openInventory(main.home);
					
					} else if(args[0].equalsIgnoreCase("cheats")) {
						main.cheats = p.getServer().createInventory(null, 27, "§4§lCheats");
						
						ItemStack backI = new ItemStack(Material.ARROW);
						ItemMeta backM = backI.getItemMeta();
						backM.setDisplayName("§7Go back");
						backI.setItemMeta(backM);
						
						ItemStack gm0 = new ItemStack(Material.MONSTER_EGG, 1, (short) 50);
						ItemMeta gm0M = gm0.getItemMeta();
						gm0M.setDisplayName("§2Gamemode §a0");
						gm0.setItemMeta(gm0M);
						
						ItemStack gm1 = new ItemStack(Material.DIAMOND, 1);
						ItemMeta gm1M = gm1.getItemMeta();
						gm1M.setDisplayName("§9Gamemode §b1");
						gm1.setItemMeta(gm1M);

						ItemStack gm2 = new ItemStack(Material.BEDROCK, 1);
						ItemMeta gm2M = gm1.getItemMeta();
						gm2M.setDisplayName("§8Gamemode §72");
						gm2.setItemMeta(gm2M);
						
						ItemStack day = new ItemStack(Material.WOOL, 1, (short) 4);
						ItemMeta dayM = day.getItemMeta();
						dayM.setDisplayName("§eDay");
						day.setItemMeta(dayM);
						
						ItemStack night = new ItemStack(Material.WOOL, 1, (short) 15);
						ItemMeta nightM = night.getItemMeta();
						nightM.setDisplayName("§8Night");
						night.setItemMeta(nightM);
						
						ItemStack downfall = new ItemStack(Material.WATER_BUCKET, 1);
						ItemMeta downfallM = downfall.getItemMeta();
						downfallM.setDisplayName("§bToggle downfall");
						downfall.setItemMeta(downfallM);
						
						main.cheats.setItem(9, backI);
						main.cheats.setItem(5, day);
						main.cheats.setItem(15, night);
						main.cheats.setItem(23, downfall);
						main.cheats.setItem(21, gm2);
						main.cheats.setItem(11, gm1);
						main.cheats.setItem(3, gm0);
						p.openInventory(main.cheats);
					
					} else if(args[0].equalsIgnoreCase("clearchat")) {
						main.clearchat = p.getServer().createInventory(null, 9, "§8§lClear Chat");
						
						ItemStack backC = new ItemStack(Material.ARROW);
						ItemMeta backM = backC.getItemMeta();
						backM.setDisplayName("§7Go back");
						backC.setItemMeta(backM);
			
						String owner = p.getName();
						ItemStack me = new ItemStack(Material.SKULL_ITEM, 1,(short) SkullType.PLAYER.ordinal());
						SkullMeta meM = (SkullMeta) me.getItemMeta();
						meM.setOwner(owner);
						meM.setDisplayName("§eClear §6your §echat");
						me.setItemMeta(meM);
						
						ItemStack all = new ItemStack(Material.GRASS);
						ItemMeta allM = all.getItemMeta();
						allM.setDisplayName("§eClear the chat of §4all §eplayers");
						all.setItemMeta(allM);
						
						main.clearchat.setItem(0, backC);
						main.clearchat.setItem(3, me);
						main.clearchat.setItem(6, all);
						p.openInventory(main.clearchat);
					}
					
				} else {
					p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GUIOpenWithClock")));
				}
			} else {
				p.sendMessage(PluginPrefix.Prefix+NoPermission);
			}
		} else {
			Bukkit.getConsoleSender().sendMessage(PluginPrefix.Prefix+ChatColor.RED+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Console.OnlyPlayers")));
		}
		return true;
	}	
}
