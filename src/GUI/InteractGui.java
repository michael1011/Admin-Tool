package GUI;

import org.bukkit.Material;
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
					if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4§lCheats")) {
						p.closeInventory();
						e.setCancelled(true);
						p.chat("/admingui cheats");
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8Home")) {
						p.closeInventory();
						e.setCancelled(true);
						p.chat("/admingui home");
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lReload the server")) {
						p.chat("/rl");
						e.setCancelled(true);
						p.closeInventory();
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8Chat clear")) {
						e.setCancelled(true);
						p.closeInventory();
						p.chat("/admingui clearchat");
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eCooming §6soon§e...")) {
						e.setCancelled(true);
					}
				}
			}
			if(e.getInventory().equals(main.home)) {
				if(e.getCurrentItem().getItemMeta() == null) {
					return;
				} else {
					if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Go back")) {
						p.closeInventory();
						e.setCancelled(true);
						p.chat("/admingui");
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eTeleport you §6home")) {
						p.chat("/home");
						e.setCancelled(true);
						p.closeInventory();
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eSet your §6home §ehere")) {
						p.chat("/home set");
						e.setCancelled(true);
						p.closeInventory();
					}
				}
			}
			if(e.getInventory().equals(main.cheats)) {
				if(e.getCurrentItem().getItemMeta() == null) {
					return;
				} else {
					if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Go back")) {
						p.closeInventory();
						e.setCancelled(true);
						p.chat("/admingui");
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Gamemode §a0")) {
						p.chat("/gamemode 0");
						e.setCancelled(true);
						p.closeInventory();
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9Gamemode §b1")) {
						p.chat("/gamemode 1");
						e.setCancelled(true);
						p.closeInventory();
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8Gamemode §72")) {
						p.chat("/gamemode 2");
						e.setCancelled(true);
						p.closeInventory();
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eDay")) {
						p.chat("/time set day");
						e.setCancelled(true);
						p.closeInventory();
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8Night")) {
						p.chat("/time set night");
						e.setCancelled(true);
						p.closeInventory();
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bToggle downfall")) {
						p.chat("/toggledownfall");
						e.setCancelled(true);
						p.closeInventory();
					}
				}
			}
			if(e.getInventory().equals(main.clearchat)) {
				if(e.getCurrentItem().getItemMeta() == null) {
					return;
				} else {
					if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Go back")) {
						p.closeInventory();
						e.setCancelled(true);
						p.chat("/admingui");
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eClear §6your §echat")) {
						p.chat("/clearchat me");
						e.setCancelled(true);
						p.closeInventory();
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eClear the chat of §4all §eplayers")) {
						p.chat("/clearchat global");
						e.setCancelled(true);
						p.closeInventory();
					}
				}
			}
		}
	}

	@EventHandler
	public void GUIdefine(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if (e.getAction() == Action.PHYSICAL || e.getItem() == null || e.getItem().getType() == Material.AIR) {
			return;
		}
		if (p.hasPermission("admintool.gui")) {		
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(e.getItem().getType() == Material.WATCH) {
					
					main.GUI = p.getServer().createInventory(null, 27, "§4§lAdmin GUI");
					
					ItemStack cheats = new ItemStack(Material.REDSTONE, 1);
					ItemMeta cheatsM = cheats.getItemMeta();
					cheatsM.setDisplayName("§4§lCheats");
					cheats.setItemMeta(cheatsM);
					
					ItemStack home = new ItemStack(Material.BED, 1);
					ItemMeta homeM = home.getItemMeta();
					homeM.setDisplayName("§8Home");
					home.setItemMeta(homeM);
					
					ItemStack rl = new ItemStack(Material.ENCHANTMENT_TABLE);
					ItemMeta rlM = rl.getItemMeta();
					rlM.setDisplayName("§c§lReload the server");
					rl.setItemMeta(rlM);
					
					ItemStack clearc = new ItemStack(Material.ANVIL, 1);
					ItemMeta clearcM = clearc.getItemMeta();
					clearcM.setDisplayName("§8Chat clear");
					clearc.setItemMeta(clearcM);
					
					ItemStack csoon = new ItemStack(Material.BARRIER, 1);
					ItemMeta csoonM = csoon.getItemMeta();
					csoonM.setDisplayName("§eCooming §6soon§e...");
					csoon.setItemMeta(csoonM);
					
					main.GUI.setItem(2, home);
					main.GUI.setItem(10, cheats);
					main.GUI.setItem(13, rl);
					main.GUI.setItem(20, clearc);
					main.GUI.setItem(16, csoon);
					
					p.openInventory(main.GUI);
					
					e.setCancelled(true);
				}
			}
		}
	}
}