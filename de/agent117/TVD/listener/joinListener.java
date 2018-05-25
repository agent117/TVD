package de.agent117.TVD.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.agent117.TVD.game.gameManager;
import de.agent117.TVD.inventory.teamsControl;
import de.agent117.TVD.main.main;

public class joinListener implements Listener{

	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.teleport(main.getLobbyLocation());
		p.getInventory().setItem(0, teamsControl.getMenuItem());
		p.setGameMode(GameMode.ADVENTURE);
		gameManager.loadGame();
		
	}
	
}
