package GUI;

import java.util.Arrays;
import java.util.List;

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

                    main.GUI = p.getServer().createInventory(null, 27, "§4§lAdmin GUI");

                    ItemStack cheats = new ItemStack(Material.REDSTONE, 1);
                    ItemMeta cheatsM = cheats.getItemMeta();
                    cheatsM.setDisplayName("§4§lCheats");
                    cheats.setItemMeta(cheatsM);

                    ItemStack home = new ItemStack(Material.BED, 1);
                    ItemMeta homeM = home.getItemMeta();
                    homeM.setDisplayName("§7Home");
                    home.setItemMeta(homeM);

                    ItemStack rl = new ItemStack(Material.ENCHANTMENT_TABLE);
                    ItemMeta rlM = rl.getItemMeta();
                    rlM.setDisplayName("§c§lReload the server");
                    rl.setItemMeta(rlM);

                    ItemStack clearc = new ItemStack(Material.ANVIL, 1);
                    ItemMeta clearcM = clearc.getItemMeta();
                    clearcM.setDisplayName("§7Chat clear");
                    clearc.setItemMeta(clearcM);

                    ItemStack ping = new ItemStack(Material.WATCH, 1);
                    ItemMeta pingM = ping.getItemMeta();
                    pingM.setDisplayName("§7Ping");
                    ping.setItemMeta(pingM);

                    ItemStack csoon = new ItemStack(Material.DIAMOND_ORE, 1);
                    ItemMeta csoonM = csoon.getItemMeta();
                    csoonM.setDisplayName("§eMore cooming §6soon§e...");
                    csoon.setItemMeta(csoonM);

                    ItemStack maint = new ItemStack(Material.BARRIER, 1);
                    ItemMeta maintM = maint.getItemMeta();
                    maintM.setDisplayName("§7Maintenance mode");
                    maint.setItemMeta(maintM);

                    main.GUI.setItem(2, home);
                    main.GUI.setItem(10, cheats);
                    main.GUI.setItem(13, rl);
                    main.GUI.setItem(20, clearc);
                    main.GUI.setItem(6, ping);
                    main.GUI.setItem(16, csoon);
                    main.GUI.setItem(24, maint);

                    p.openInventory(main.GUI);

				} else if(args.length == 1) {
					
					if(args[0].equalsIgnoreCase("home")) {
						main.home = p.getServer().createInventory(null, 9, "§7§lHome");
						
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
						
						ItemStack fly = new ItemStack(Material.WOOL, 1, (short) 0);
						ItemMeta flyM = fly.getItemMeta();
						flyM.setDisplayName("§fToggle §efly §fmode");
						fly.setItemMeta(flyM);
						
						main.cheats.setItem(9, backI);
						main.cheats.setItem(5, day);
						main.cheats.setItem(15, night);
						main.cheats.setItem(23, downfall);
						main.cheats.setItem(21, gm2);
						main.cheats.setItem(11, gm1);
						main.cheats.setItem(3, gm0);
						main.cheats.setItem(13, fly);
						p.openInventory(main.cheats);
					
					} else if(args[0].equalsIgnoreCase("clearchat")) {
						main.clearchat = p.getServer().createInventory(null, 9, "§7§lClear Chat");
						
						ItemStack backC = new ItemStack(Material.ARROW);
						ItemMeta backM = backC.getItemMeta();
						backM.setDisplayName("§7Go back");
						backC.setItemMeta(backM);
						
						ItemStack me = new ItemStack(Material.SKULL_ITEM, 1,(short) SkullType.PLAYER.ordinal());
						SkullMeta meM = (SkullMeta) me.getItemMeta();
						meM.setOwner(sender.getName());
						meM.setDisplayName("§eClear §6your §echat");
						me.setItemMeta(meM);
						
						ItemStack all = new ItemStack(Material.GRASS);
						ItemMeta allM = all.getItemMeta();
						allM.setDisplayName("§eClear the chat of §4all §eplayers");
						all.setItemMeta(allM);
						
						ItemStack one = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.ZOMBIE.ordinal());
						SkullMeta oneM = (SkullMeta) one.getItemMeta();
						oneM.setDisplayName("§6Choose §ea player");
						one.setItemMeta(oneM);
						
						main.clearchat.setItem(0, backC);
						main.clearchat.setItem(2, me);
						main.clearchat.setItem(4, all);
						main.clearchat.setItem(6, one);
						p.openInventory(main.clearchat);
						
					} else if(args[0].equalsIgnoreCase("clearchatall")) {
						int i = 8;
						
						main.allclearchat = p.getServer().createInventory(null, 54, "§7§lChoose a player");
						
						for(Player all : plugin.getServer().getOnlinePlayers()) {
							i++;
							
							ItemStack player = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
							SkullMeta playerM = (SkullMeta) player.getItemMeta();
							playerM.setOwner(all.getName());
							playerM.setDisplayName(all.getDisplayName());
							player.setItemMeta(playerM);
							
							main.allclearchat.setItem(i, player);
						}
						
						ItemStack back = new ItemStack(Material.ARROW);
						ItemMeta backM = back.getItemMeta();
						backM.setDisplayName("§7Go back");
						back.setItemMeta(backM);
						
						main.allclearchat.setItem(0, back);
						
						if(i > 53) {
							p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GUITooManyPlayer")));
						} else {
							p.openInventory(main.allclearchat);
						}
						
					} else if(args[0].equalsIgnoreCase("ping")) {
						main.ping = p.getServer().createInventory(null, 9, "§7§lPing");

						ItemStack back = new ItemStack(Material.ARROW);
						ItemMeta backM = back.getItemMeta();
						backM.setDisplayName("§7Go back");
						back.setItemMeta(backM);
						
						ItemStack me = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
						SkullMeta meM = (SkullMeta) me.getItemMeta();
						meM.setOwner(sender.getName());
						meM.setDisplayName("§eSee §6your §eping");
						me.setItemMeta(meM);
						
						ItemStack all = new ItemStack(Material.GRASS, 1);
						ItemMeta allM = all.getItemMeta();
						allM.setDisplayName("§6Choose §ea Player");
						all.setItemMeta(allM);
						
						main.ping.setItem(0, back);
						main.ping.setItem(3, me);
						main.ping.setItem(6, all);
						p.openInventory(main.ping);
					
					} else if(args[0].equalsIgnoreCase("pingall")) {
						int i = 8;
						
						main.allPing = p.getServer().createInventory(null, 54, "§7§lChoose a player");
								
						for(Player all : plugin.getServer().getOnlinePlayers()) {
							i++;
							
							ItemStack player = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
							SkullMeta playerM = (SkullMeta) player.getItemMeta();
							playerM.setOwner(all.getName());
							playerM.setDisplayName(all.getDisplayName());
							player.setItemMeta(playerM);
							
							main.allPing.setItem(i, player);
						}
						
						ItemStack back = new ItemStack(Material.ARROW);
						ItemMeta backM = back.getItemMeta();
						backM.setDisplayName("§7Go back");
						back.setItemMeta(backM);
						
						main.allPing.setItem(0, back);
						
						if(i > 53) {
							p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GUITooManyPlayer")));
						} else {
							p.openInventory(main.allPing);
						}
						
					} else if(args[0].equalsIgnoreCase("maint")) {
						main.maint = p.getServer().createInventory(null, 9, "§7§lMaintenance mode");

						ItemStack back = new ItemStack(Material.ARROW);
						ItemMeta backM = back.getItemMeta();
						backM.setDisplayName("§7Go back");
						back.setItemMeta(backM);

						List<String> toggleN = Arrays.asList("", "§7Enable it when its disabled.", "§7Disable it when its enabled.");
						ItemStack toggle = new ItemStack(Material.BARRIER);
						ItemMeta toggleM = toggle.getItemMeta();
						toggleM.setDisplayName("§6Toogle §ethe maintenance mode");
						toggleM.setLore(toggleN);
						toggle.setItemMeta(toggleM);

						ItemStack enable = new ItemStack(Material.WOOL, 1, (byte) 5);
						ItemMeta enableM = enable.getItemMeta();
						enableM.setDisplayName("§aEnable §ethe maintenance mode");
						enable.setItemMeta(enableM);

						ItemStack disable = new ItemStack(Material.WOOL, 1, (byte) 14);
						ItemMeta disableM = enable.getItemMeta();
						disableM.setDisplayName("§4Disable §ethe maintenance mode");
						disable.setItemMeta(disableM);

						main.maint.setItem(0, back);
						main.maint.setItem(2, toggle);
						main.maint.setItem(5, enable);
						main.maint.setItem(7, disable);
						p.openInventory(main.maint);

					} else if(args[0].equalsIgnoreCase("give")) {


					} else {
						p.sendMessage(PluginPrefix.Prefix+ChatColor.translateAlternateColorCodes('&', plugin.messages.getString("Players.GUIOpenWithClock")));
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
