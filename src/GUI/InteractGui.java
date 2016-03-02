package GUI;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import main.main;

public class InteractGui implements Listener {

	private main plugin;

	public InteractGui(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void GUIset(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		if (p.hasPermission("admintool.gui")) {
			if(e.getInventory().equals(main.GUI)) {
				if(e.getCurrentItem().getItemMeta() == null) {
					return;
				} else {
                    if(e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        String var = e.getCurrentItem().getItemMeta().getDisplayName();
                        e.setCancelled(true);
                        p.closeInventory();
                        if(var.equals("§4§lCheats")) {
                            p.chat("/admingui cheats");
                        } else if(var.equals("§7Home")) {
                            p.chat("/admingui home");
                        } else if(var.equals("§c§lReload the server")) {
                            p.chat("/rl");
                        } else if(var.equals("§7Chat clear")) {
                            p.chat("/admingui clearchat");
                        } else if(var.equals("§7Ping")) {
                            p.chat("/admingui ping");
                        } else if(var.equals("§7Maintenance mode")) {
                            p.chat("/admingui maint");
                        }
                    }
				}
			}
			if(e.getInventory().equals(main.home)) {
				if(e.getCurrentItem().getItemMeta() == null) {
					return;
				} else {
                    if(e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        String var = e.getCurrentItem().getItemMeta().getDisplayName();
                        e.setCancelled(true);
                        p.closeInventory();
                        if(var.equals("§7Go back")) {
                            p.chat("/admingui");
                        } else if(var.equals("§eTeleport you §6home")) {
                            p.chat("/home");
                        } else if(var.equals("§eSet your §6home §ehere")) {
                            p.chat("/home set");
                        }
                    }
				}
			}
			if(e.getInventory().equals(main.cheats)) {
				if(e.getCurrentItem().getItemMeta() == null) {
					return;
				} else {
                    if(e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        String var = e.getCurrentItem().getItemMeta().getDisplayName();
                        e.setCancelled(true);
                        p.closeInventory();
                        if(var.equals("§7Go back")) {
                            p.chat("/admingui");
                        } else if(var.equals("§2Gamemode §a0")) {
                            p.setGameMode(GameMode.SURVIVAL);
                        } else if(var.equals("§9Gamemode §b1")) {
                            p.setGameMode(GameMode.CREATIVE);
                        } else if(var.equals("§8Gamemode §72")) {
                            p.setGameMode(GameMode.ADVENTURE);
                        } else if(var.equals("§eDay")) {
                            World world = p.getWorld();
                            world.setTime(1000);
                        } else if(var.equals("§8Night")) {
                            World world = p.getWorld();
                            world.setTime(13000);
                        } else if(var.equals("§bToggle downfall")) {
                            p.chat("/toggledownfall");
                        } else if(var.equals("§fToggle §efly §fmode")) {
                            if(p.getAllowFlight()) {
                                p.setAllowFlight(false);
                            } else if(!p.getAllowFlight()) {
                                p.setAllowFlight(true);
                            }
                        }
                    }
				}
			}
			if(e.getInventory().equals(main.clearchat)) {
				if(e.getCurrentItem().getItemMeta() == null) {
					return;
				} else {
                    if(e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        String var = e.getCurrentItem().getItemMeta().getDisplayName();
                        e.setCancelled(true);
                        p.closeInventory();
                        if(var.equals("§7Go back")) {
                            p.chat("/admingui");
                        } else if(var.equals("§eClear §6your §echat")) {
                            p.chat("/clearchat me");
                        } else if(var.equals("§eClear the chat of §4all §eplayers")) {
                            p.chat("/clearchat global");
                        } else if(var.equals("§6Choose §ea player")) {
                            p.chat("/admingui clearchatall");
                        }
                    }
				}
			}
			if(e.getInventory().equals(main.allclearchat)) {
				if(e.getCurrentItem().getItemMeta() == null) {
					return;
				} else {
                    if(e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        String var = e.getCurrentItem().getItemMeta().getDisplayName();
                        e.setCancelled(true);
                        p.closeInventory();
                        if(var.equals("§7Go back")) {
                            p.chat("/admingui");
                        } else if(e.getCurrentItem().getType() != Material.AIR){
                            Player target = plugin.getServer().getPlayer((e.getInventory().getItem(e.getSlot()).getItemMeta().getDisplayName().replace(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("Settings.OPPrefix")), "")));
                            if(target != null) {
                                String targetC = target.getName();
                                p.chat("/clearchat "+targetC);
                            }
                        }
                    }
				}
			}
			if(e.getInventory().equals(main.ping)) {
				if(e.getCurrentItem().getItemMeta() == null) {
					return;
				} else {
                    if(e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        String var = e.getCurrentItem().getItemMeta().getDisplayName();
                        e.setCancelled(true);
                        p.closeInventory();
                        if(var.equals("§7Go back")) {
                            p.chat("/admingui");
                        } else if(var.equals("§eSee §6your §eping")) {
                            p.chat("/ping");
                        } else if(var.equals("§6Choose §ea Player")) {
                            p.chat("/admingui pingall");
                        }
                    }
				}
			}
			if(e.getInventory().equals(main.allPing)) {
				if(e.getCurrentItem().getItemMeta() == null) {
					return;
				} else {
                    if(e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        String var = e.getCurrentItem().getItemMeta().getDisplayName();
                        e.setCancelled(true);
                        p.closeInventory();
                        if(var.equals("§7Go back")) {
                            p.chat("/admingui");
                        } else if(e.getCurrentItem().getType() != Material.AIR){
                            Player target = plugin.getServer().getPlayer((e.getInventory().getItem(e.getSlot()).getItemMeta().getDisplayName().replace(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("Settings.OPPrefix")), "")));
                            if(target != null) {
                                String targetC = target.getName();
                                p.chat("/ping "+targetC);
                            }
                        }
                    }
				}
			}
			if(e.getInventory().equals(main.maint)) {
				if(e.getCurrentItem().getItemMeta() != null) {
                    if(e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        String var = e.getCurrentItem().getItemMeta().getDisplayName();
                        e.setCancelled(true);
                        p.closeInventory();
                        if(var.equals("§7Go back")) {
                            p.chat("/admingui");
                        } else if(var.equals("§6Toogle §ethe maintenance mode")) {
                            p.chat("/maint");
                        } else if(var.equals("§aEnable §ethe maintenance mode")) {
                            p.chat("/maint on");
                        } else if(var.equals("§4Disable §ethe maintenance mode")) {
                            p.chat("/maint off");
                        }
                    }
				}
			}
		}
	}

	@EventHandler
	public void GUIdefine(PlayerInteractEvent e) {
		Player p = e.getPlayer();

        if (p.hasPermission("admintool.gui")) {
            if (e.getItem() != null && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                if(e.getItem().getType() == Material.WATCH) {

                    e.setCancelled(true);
                    p.chat("/admingui");

                }
            }
        }
    }
}
