package de.agent117.TVD.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import de.agent117.TVD.game.teamManager;
import de.agent117.TVD.mechanic.timeManager;

public class protectionListener implements Listener{

	@EventHandler
	public void onDamageEntity(EntityDamageByEntityEvent e) {
		e.setCancelled(onDmg());
		if(e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			Player p1 = (Player)e.getDamager();
			Player p2 = (Player)e.getEntity();
			
			if(teamManager.containsTeam(p1) && teamManager.containsTeam(p2))
			if(teamManager.arePlayersInTeam(p1, p2)) {
				e.setCancelled(true);
			}
		}
		
	}
	
	@EventHandler
	public void onDamageBlock(EntityDamageByBlockEvent e) {
		e.setCancelled(onDmg());
	}
	
	@EventHandler
	public void onDamageBlock2(EntityDamageEvent e) {
		e.setCancelled(onDmg());
	}
	
	
	private boolean onDmg() {
		return timeManager.isProtection();
	}
	
}
