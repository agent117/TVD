package de.agent117.TVD.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import de.agent117.TVD.game.gameManager;
import de.agent117.TVD.inventory.teamsControl;

public class actionListener implements Listener{

	
	@EventHandler
	public void onCLick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getItem() != null) {
			ItemStack i = e.getItem();
			if(i.equals(teamsControl.getMenuItem())) {					//Menu Inventar
				p.openInventory(teamsControl.getMenuInventory());
				e.setCancelled(true);
			}
			
			
		}
		
		if(e.getClickedBlock() != null) {
			if(e.getClickedBlock().getType().equals(gameManager.chest)) {
				Location l = e.getClickedBlock().getLocation();
				chestClick.onClick(p, l);
				e.setCancelled(true);
			}
			
			if(e.getClickedBlock().getType().equals(gameManager.supply)) {
				Location l = e.getClickedBlock().getLocation();
				supplyClick.onClick(p, l);
				e.setCancelled(true);
			}
		}
		
	}
}
