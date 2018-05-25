package de.agent117.TVD.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.agent117.TVD.game.gameManager;

public class moveListener implements Listener{
	private static boolean active = false;
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if(gameManager.containsAlivePlayer(e.getPlayer()) && active) {
			e.setCancelled(true);
		}
	}
	
	public static void setActive(boolean activeB) {
		active = activeB;
	}
	
}
