package de.agent117.TVD.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.agent117.TVD.game.Teams;
import de.agent117.TVD.game.teamManager;
import de.agent117.TVD.inventory.teamsControl;

public class invClickListener implements Listener{

	
	@EventHandler
	public void onInvCLick(InventoryClickEvent e) {
		if(e.getWhoClicked() instanceof Player) {
			Player p = (Player)e.getWhoClicked();
			if(e.getCurrentItem() != null && e.getClickedInventory() != null && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().hasLore()) {
				Boolean b1 = e.getClickedInventory().getContents().length == teamsControl.getMenuInventory().getContents().length;
				Boolean b2 = e.getClickedInventory().getTitle().equals(teamsControl.getMenuInventory().getTitle());
				
				if(b1 && b2) {
					ItemStack i = e.getCurrentItem();
					
					if(i.getItemMeta().getLore().get(1).equals("")) {
						teamManager.addPlayerToTeam(p, Teams.getTeam(i.getItemMeta().getDisplayName()), true);
						e.setCancelled(true);
					}
				}
				
				
			}
		}
	}
}
