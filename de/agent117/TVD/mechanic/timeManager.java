package de.agent117.TVD.mechanic;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.scheduler.BukkitTask;

import de.agent117.TVD.game.gameManager;
import de.agent117.TVD.main.main;

public class timeManager {
	private static boolean isProtection = false;
	
	public static void setFreeze(int sec, boolean see) {
		gameManager.freezePlayer(true);
		startFreezeTimer(sec);
		Bukkit.broadcastMessage("You are frozen for 10 Seconds");		//TODO config
		if(see) {
			setCountdown(sec, false, false);
		}
	}
	
	public static void startElytra() {
		startElytraTimer(60);
	}
	
	public static void setProtection(int sec) {
		isProtection = true;
		startProtectionTimer(sec);
		Bukkit.broadcastMessage("You are protected for 20 Seconds");		//TODO config
		setCountdown(sec, false, false);
	}
	
	public static void setProtection(boolean check) {
		isProtection = check;
	}

	public static void startEvent() {
		startEventTimer();
	}
	
	public static BukkitTask setCountdown(int sec,boolean activate, boolean isEnd) {
		return startCountdownTimer(sec, activate, isEnd);
	}
	
	
	public static boolean isProtection() {
		return isProtection;
	}
	
	
	
	
	
	
	private static void startElytraTimer(int sec) {
		Bukkit.getScheduler().runTaskLater(main.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				
				gameManager.removeElytra();
				
			}
		}, sec * 20L);	
	}
	
	
	private static void startFreezeTimer(int sec) {
		Bukkit.getScheduler().runTaskLater(main.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				Bukkit.broadcastMessage("The plain will be destroyed in 10 Seconds");		//TODO config
				gameManager.freezePlayer(false);
				
			}
		}, sec * 20L);
	}
	
	private static void startProtectionTimer(int sec) {
		Bukkit.getScheduler().runTaskLater(main.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				
				isProtection = false;
				gameManager.destroyShip();
				
			}
		}, sec * 20L);
	}
	
	private static BukkitTask startCountdownTimer(int sec, boolean activate, boolean isEnd) {
		BukkitTask t1;
		t1 = Bukkit.getScheduler().runTaskTimer(main.getPlugin(), new Runnable() {
			int count = sec;
			@Override
			public void run() {
				if(count >= 0) {
					gameManager.setPlayerLevel(count);
					count--;
				} 
			}
		},0, 20L);
		
		Bukkit.getScheduler().runTaskLater(main.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				gameManager.setPlayerLevel(0);
				t1.cancel();
				if(activate) {
					if(isEnd) {
						gameManager.quitPlayer();
					} else {
						gameManager.startGame();
					}
				}
				
			}
		}, sec * 20L);
		
		return t1;
	}
	
	
	private static void startEventTimer() {
		Bukkit.getScheduler().runTaskTimer(main.getPlugin(), new Runnable() {
			int eventCount = 0;
			int borderCount = 0;
			
			@Override
			public void run() {
				
				switch(eventCount) {
				case 10: 
					gameManager.spawnSupplyChest();
					break;
				case 20:
					gameManager.refuelSpawnChest();
					break;
				case 30:
					gameManager.spawnSupplyChest();
					break;
				case 45:
					gameManager.teleportToDeathGame();
					break;
				}
				
				
				if(borderCount == 10) {
					gameManager.updateBorder();
					borderCount = 0;
				}
				
				borderCount++;
				eventCount++;
			}
		},0, 60 * 20L);
	}
	
	
	
	
}
