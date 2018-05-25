package de.agent117.TVD.listener;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import de.agent117.TVD.game.IText;
import de.agent117.TVD.game.gameManager;
import de.agent117.TVD.game.teamManager;
import de.agent117.TVD.main.main;

public class killListener implements Listener, IText{
	
	@EventHandler
	public void onLeft(PlayerQuitEvent e) {
		if(gameManager.getRunning()) {
			Player p = e.getPlayer();
			gameManager.deathPlayer(p);
			gameManager.updateBorder();
			checkEnd();
		} else {
			gameManager.loadGame();
		}
	}
	
	@EventHandler
	public void onKill(EntityDeathEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player)e.getEntity();
			gameManager.deathPlayer(p);
			gameManager.updateBorder();
			checkEnd();
		} else {
			replaceLoot(e.getDrops());
		}
	}
	
	private static void replaceLoot(List<ItemStack> drops) {	
		Material[] ori = {Material.RAW_BEEF, Material.RAW_CHICKEN, Material.RABBIT, Material.MUTTON, Material.PORK};
		Material[] repl = {Material.COOKED_BEEF, Material.COOKED_CHICKEN, Material.COOKED_RABBIT, Material.COOKED_MUTTON, Material.GRILLED_PORK};	
		
		for(int count = 0; count < drops.size(); count++) {
			ItemStack item = drops.get(count);
			int amount = item.getAmount();
			int c = 0;
			for(Material m : ori) {
				if(m.equals(item.getType())) {
					drops.remove(count);
					drops.add(new ItemStack(repl[c], amount));
					break;
				}
				c++;
			}
		}
			
	}
	
	
	public static void checkDeathGame() {
		if(gameManager.countAlivePlayers() < 5) {		//TODO anpassen
			gameManager.teleportToDeathGame();
			Bukkit.broadcastMessage(startDeathgame);
		}
	}
	
	public static void checkEnd() {
		if(main.getModus() == teamManager.oneTeam) {
			if(gameManager.countAlivePlayers() == 1) {
				gameManager.getWinner();
			}
		}
		
		if(main.getModus() == teamManager.twoTeam) {
			if((gameManager.countAlivePlayers() == 2 && gameManager.checkLastTwoPlayer()) || gameManager.countAlivePlayers() == 1) {
				gameManager.getWinner();
			}
		}
	}
}
