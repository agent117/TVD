package de.agent117.TVD.game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;

import de.agent117.TVD.inventory.teamsControl;
import de.agent117.TVD.listener.chestClick;
import de.agent117.TVD.listener.moveListener;
import de.agent117.TVD.listener.supplyClick;
import de.agent117.TVD.main.main;
import de.agent117.TVD.main.saveInformations;
import de.agent117.TVD.mechanic.IPath;
import de.agent117.TVD.mechanic.timeManager;
import ventrixco.de.tokensapi.Tokens;

@SuppressWarnings("deprecation")
public class gameManager implements IPath, IText{
	private static boolean isRunning = false;
	private static BukkitTask t1 = null;
	private static Location middle;
	private static Location shipMiddle;
	public static Material chest = Material.CHEST;
	public static Material supply = Material.ENDER_CHEST;
	
	private static ArrayList<Player> alivePlayer = new ArrayList<Player>();
	private static ArrayList<Location> spawnLocs = new ArrayList<Location>();
	
	public static void loadGame() {
		
		if(canStart()) {
			t1 = timeManager.setCountdown(60, true, false);
		} else {
			if(t1 != null) {
				t1.cancel();
				setPlayerLevel(60);
			}
		}
	}
	
	public static void startGame(){
		int count = 0;
		World w = getMiddle().getWorld();
		refuelSpawnChest();
		teamManager.setLastPlayerInTeams();
		
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			alivePlayer.add(p);
			p.getInventory().clear();
			p.setHealth(20);
			p.setFoodLevel(20);
			p.getInventory().addItem(teamsControl.getElytra());
			p.teleport(spawnLocs.get(count));
			p.setGameMode(GameMode.SURVIVAL);
			count++;
		}
		
		timeManager.setFreeze(10, false); 			//TODO Zeit anpassen
		timeManager.setProtection(20);
		timeManager.startElytra();
		w.getWorldBorder().setDamageAmount(2);
		w.getWorldBorder().setCenter(middle);
		w.getWorldBorder().setSize(countPlayers() * 100);
		
		
		timeManager.startEvent();
		isRunning = true;
	}
	
	public static void spawnShip() {
		long start = System.currentTimeMillis();
		Random rnd = new Random();
		
		World w = Bukkit.getWorld("lobby");
		int ym = w.getMaxHeight() - 20;
		
		Location l = new Location(w, rnd.nextInt(8000)+2000, ym, rnd.nextInt(8000)+2000);  		//TODO Weltname einfügen
		
		Boolean b1 = false;
		Boolean b2 = false;
		Boolean b3 = false;
		Boolean b4 = false;
		Boolean b5 = false;
		Boolean b6 = false;
		
		
		
		do {
			 b1 = l.clone().add(69, 0, 0).getBlock().getType().equals(Material.AIR);
			 b2 = l.clone().add(-69, 0, 0).getBlock().getType().equals(Material.AIR);
			 
			 b5 = l.clone().add(0, 17, 0).getBlock().getType().equals(Material.AIR);
			 b6 = l.clone().add(0, -14, 0).getBlock().getType().equals(Material.AIR);
			 
			 b3 = l.clone().add(0, 0, 115).getBlock().getType().equals(Material.AIR);
			 b4 = l.clone().add(0, 0, -10).getBlock().getType().equals(Material.AIR);
			 
			 l.add(1, 0, 1);
		}while(!(b1 & b2 & b3 & b4 & b5 &b6));
		
		setMiddle(l.clone().add(0, 0, 50));
		
		
		
		WorldEditPlugin we = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
		File schematic = new File("plugins/WorldEdit/schematics/lobby.schematic");
		EditSession session = we.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(w), 10000000);

		
		try {
			MCEditSchematicFormat.getFormat(schematic).load(schematic).paste(session,new Vector(l.getBlockX(), l.getBlockY(), l.getBlockZ()) , false);
		} catch (MaxChangedBlocksException | DataException | IOException e) {
			e.printStackTrace();
		}
		
		l.add(70, 17, 115);
				
		shipMiddle = l;
		
		for(int x = 0; x < 170; x++) {
        	for(int y = 0; y < 32; y++) {
        		for(int z = 0; z < 126; z++) {
        			Location l2 = l.clone().subtract(x, y, z);
		        	if(l2.getBlock().getType().equals(gameManager.chest)) {
		        		chestClick.addLocation(l2);
		        	}
		        	
		        	
		        	if(l2.getBlock().getType().equals(Material.DIAMOND_BLOCK)) {
		        		l2.getBlock().setType(Material.AIR);
		        		addSpawnLocation(l2);
		        	}
		        	
		        	
		        }
	        }
        }
				

		
		
		
		
		
		
		
		long end = System.currentTimeMillis();
		
		
		 System.out.println("Completed at " + l.getBlockX() + " " + l.getBlockY() + " " + l.getBlockZ() + " in " + (end - start) + "ms");
		
		
	}
	
	public static void destroyShip() {
		for(int i = 0; i < 5; i++) {
			for(Player pp : Bukkit.getOnlinePlayers()) {
				Bukkit.getScheduler().runTaskLater(main.getPlugin(), new Runnable() {
					@Override
					public void run() {
						pp.playSound(pp.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1f, 1f);
						pp.playSound(pp.getLocation(), Sound.ENTITY_FIREWORK_LARGE_BLAST_FAR, 0.4f, 10f);
					}
				}, 10 * i);
			}
		}
		
		
		for(int x = 0; x < 170; x++) {
        	for(int y = 0; y < 32; y++) {
        		for(int z = 0; z < 126; z++) {
        			Location l2 = shipMiddle.clone().subtract(x, y, z);
        			l2.getBlock().setType(Material.AIR);
		        }
	        }
        }
	}
	
	
	public static boolean getRunning() {
		return isRunning;
	}
	
	public static boolean containsAlivePlayer(Player p) {
		return alivePlayer.contains(p);
	}
	
	public static void updateBorder() {
		double size = getMiddle().getWorld().getWorldBorder().getSize();
		
		getMiddle().getWorld().getWorldBorder().setSize(size - 100);
//		
//		int size = (int) (alivePlayer.size() * 100);
//		w.getWorldBorder().setSize(size, 100);
	}
	
	public static void setWinner(Teams team) {
		Player[] player = teamManager.getPlayerInTeam(team);
		
		
		if(player[0] != null) {
//			Tokens.addTokens(player[0].getUniqueId().toString(), 100);
			Bukkit.broadcastMessage(playerWin.replaceAll("%player%", player[0].getDisplayName()));
		} 
		
		if(player[1] != null) {
//			Tokens.addTokens(player[1].getUniqueId().toString(), 100);
			Bukkit.broadcastMessage(playerWin.replaceAll("%player%", player[1].getDisplayName()));
		} 
		
		teleportToLobby();
		
	}
	
	public static void getWinner() {
		setWinner(teamManager.getTeamFromPlayer(alivePlayer.get(0)));
		
	}
	
	
	
	public static void deathPlayer(Player p) {
		alivePlayer.remove(p);
		p.setGameMode(GameMode.SPECTATOR);
	}
	
	public static void teleportToDeathGame() {
		Random rnd = new Random();
		int range = 20;			//TODO anpassen 
		for(Player p : alivePlayer) {
			int x = rnd.nextInt(2 * range) - range;
			int z = rnd.nextInt(2 * range) - range;
			
			Location d = main.getDeathGameLocation().clone();
			p.teleport(d.add(x, 0, z));
		}
		Bukkit.broadcastMessage(startDeathgame);
		timeManager.setFreeze(10, true);
	}
	
	public static void teleportToLobby() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.teleport(main.getLobbyLocation());
		}
		
		timeManager.setCountdown(10, true, true);
		
	}
	
	
	public static void quitPlayer() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.performCommand("hub");   //TODO anpassen
		}
		Bukkit.getScheduler().cancelAllTasks();
		
		saveInformations.saveLobbyLoc(main.getLobbyLocation());
		saveInformations.saveDeathGameLoc(main.getDeathGameLocation());
		
		Bukkit.getServer().shutdown();
	}
	
	public static void spawnSupplyChest() {
		Random rnd = new Random();
		int br = getBorderRadius();
		int rndX = rnd.nextInt(br);
		int rndZ = rnd.nextInt(br);
		
		int x = (rndX - br/2) + getMiddle().getBlockX(); 
		int z = (rndZ - br/2) + getMiddle().getBlockZ(); 
		
		Location loc = new Location(getMiddle().getWorld(), x, getMiddle().getWorld().getMaxHeight(), z);
		Location finalLoc = loc;
		while(checkBlocks(loc)) {
			loc.subtract(0, 1, 0);		
			finalLoc = loc; //.subtract(0, 1, 0);
		}
		
		finalLoc.getBlock().setType(supply);
		supplyClick.addLocation(finalLoc);
		supplyClick.createInventories();
		
		String msg = spawnSupplyChest;
			msg = msg.replaceAll("%x%", x + "");
			msg = msg.replaceAll("%y%", finalLoc.getBlockY() + "");
			msg = msg.replaceAll("%z%", z + "");
		Bukkit.broadcastMessage(msg);
	}
	
	public static void refuelSpawnChest() {
		chestClick.createInventories();
		Bukkit.broadcastMessage(refillChest);
	}
	
	public static void setPlayerLevel(int lvl) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.setLevel(lvl);
		}
	}
	
	
	public static boolean canStart() {
		int count = countPlayers();
		
		if(count >= 12 && count <= 24) {
			if(main.getLobbyLocation() != null &&
			   getMiddle() != null &&
			   main.getDeathGameLocation() != null &&
			   spawnLocs.size() <= 24 && 
			   spawnLocs.size() >= 12 ) {
					return true;
			}
			
		}
		
		return false;
	}
	
	public static int getBorderRadius() {
		return (int) getMiddle().getWorld().getWorldBorder().getSize();
//		return (int) (countAlivePlayers() * 41.7);
//		return (int) (countAlivePlayers() * 100);
	}
	
	public static Location getMiddle() {
		return middle;
	}
	
	public static ArrayList<Location> getPlayerSpawnLocs() {
		return spawnLocs;
	}
	
	public static void setMiddle(Location loc) {
		middle = loc;
	}
	
	
	public static int countAlivePlayers() {
		return alivePlayer.size();
	}
	
	public static boolean checkLastTwoPlayer() {
		Player p1 = alivePlayer.get(0);
		Player p2 = alivePlayer.get(1);
		if(teamManager.arePlayersInTeam(p1, p2)) {
			return true;
		}
		
		return false;
	}
	
	public static void freezePlayer(boolean activate) {
		moveListener.setActive(activate);
	}
	
	public static void setSpawnLocs(ArrayList<Location> locs) {
		spawnLocs = locs;
	}
	
	public static boolean addSpawnLocation(Location loc) {
		
		if(spawnLocs.size() < (24 / teamManager.getMaxTeamSize())) {
			spawnLocs.add(loc);
			return true;
		}
		
		return false;
	}
	
	public static boolean removeSpawnLocation(Location loc) {
		if(spawnLocs.contains(loc)) {
			spawnLocs.remove(loc);
			return true;
		}
		
		return false;
	}
	
	
	public static void removeElytra() {
		
		for(Player p : alivePlayer) {
			for(ItemStack i : p.getInventory()) {
				if(i != null) {
					if(i.getType().equals(teamsControl.getElytra().getType())) {
						p.getInventory().remove(i);
					}
				}
			}
			
			if(p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType().equals(teamsControl.getElytra().getType())) {
				p.getInventory().getChestplate().setAmount(0);
			}
		}
		
	}
	

	
	private static int countPlayers() {
		return Bukkit.getOnlinePlayers().size();
	}
	
	private static boolean checkBlocks(Location l2) {
		Location l = l2.clone();
		Boolean b1 = getMiddle().getWorld().getBlockAt(l).getType().equals(Material.AIR);
		Boolean b2 = getMiddle().getWorld().getBlockAt(l.add(0, 1, 0)).getType().equals(Material.AIR);
		Boolean b3 = getMiddle().getWorld().getBlockAt(l.add(0, -2, 0)).getType().equals(Material.AIR);
		
		if(b1 && b2 && !b3) {
			return false;
		} else {
			return true;
		}
	}

}
