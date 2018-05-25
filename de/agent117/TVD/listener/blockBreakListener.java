package de.agent117.TVD.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;


public class blockBreakListener implements Listener{

	@EventHandler
	public void onDestroy(BlockBreakEvent e) {
		
		
		if(e.getBlock() != null) {
			Material m = e.getBlock().getType();
			
			if(m.equals(Material.COAL_ORE)) {
				e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.TORCH));
				e.getPlayer().giveExp(1);
				e.getBlock().setType(Material.AIR);
			}
			
			if(m.equals(Material.IRON_ORE)) {
				e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
				e.getPlayer().giveExp(3);
				e.getBlock().setType(Material.AIR);
			}
			
			if(m.equals(Material.GOLD_ORE)) {
				e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
				e.getPlayer().giveExp(3);
				e.getBlock().setType(Material.AIR);
			}
		}
	}
}
