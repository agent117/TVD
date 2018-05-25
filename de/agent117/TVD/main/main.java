package de.agent117.TVD.main;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import com.sk89q.worldedit.schematic.SchematicFormat;

import de.agent117.TVD.commands.saveAll;
import de.agent117.TVD.commands.setDeathGameLoc;
import de.agent117.TVD.commands.setLobbySpawn;
import de.agent117.TVD.game.gameManager;
import de.agent117.TVD.listener.actionListener;
import de.agent117.TVD.listener.blockBreakListener;
import de.agent117.TVD.listener.chestClick;
import de.agent117.TVD.listener.invClickListener;
import de.agent117.TVD.listener.joinListener;
import de.agent117.TVD.listener.killListener;
import de.agent117.TVD.listener.moveListener;
import de.agent117.TVD.listener.protectionListener;
import de.agent117.TVD.mechanic.IPath;
import de.agent117.TVD.mechanic.configManager;
import de.agent117.TVD.mechanic.timeManager;
import net.md_5.bungee.api.ChatColor;

public class main extends JavaPlugin implements IPath{
	private static Plugin p;
	private static String splitChar = ";";
	public final static String prefix = ChatColor.GREEN + "[TVD]: " + ChatColor.GRAY;
	private static int MODUS;
	
	private static Location lobby = null;
	private static Location deathGame = null;
	
	@Override
	public void onEnable() {
		p = this;
		MODUS = configManager.getStandardConfig().getInt(teamModusPath);
		Bukkit.getConsoleSender().sendMessage(prefix + "selected Modus: " + MODUS); 
		
		createWorld();
		configManager.checkForConfigFiles();
		loadLocations.loadAll();
		
		//Listener
		Bukkit.getServer().getPluginManager().registerEvents(new actionListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new blockBreakListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new invClickListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new joinListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new killListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new protectionListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new moveListener(), this);
		
		//Commands
		getCommand("save").setExecutor(new saveAll());
		getCommand("setLobbySpawn").setExecutor(new setLobbySpawn());
		getCommand("setDeathGameSpawn").setExecutor(new setDeathGameLoc());
		
		timeManager.setProtection(true);
		
	}
	
	



	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player)sender;
			
			
			if(label.equalsIgnoreCase("test2")) {
				gameManager.destroyShip();
			}
			
			
			
			if(label.equalsIgnoreCase("fix")) {
				p.setWalkSpeed(0.2f);
			}
			
			if(label.equalsIgnoreCase("test")) {
				
				gameManager.spawnShip();
			}
			
			if(label.equalsIgnoreCase("startgame")) {
				timeManager.setCountdown(6, true, false);
			}
			
			if(label.equalsIgnoreCase("refillspawn")) {
				gameManager.refuelSpawnChest();
			}
			
			if(label.equalsIgnoreCase("spawnsupply")) {
				gameManager.spawnSupplyChest();
			}
			
			if(label.equalsIgnoreCase("end")) {
				killListener.checkEnd();
			}
			
			
			if(label.equalsIgnoreCase("addspawns")) {
				for(int i = 0; i < 24; i++) {
					gameManager.addSpawnLocation(p.getLocation());
				}
			}
			
			
			
			
			
		}
		
		return false;
	}
	
	
	
	
	
	public static String getSplitChar() {
		return splitChar;
	}
	
	public static Plugin getPlugin() {
		return p;
	}
	
	public static int getModus() {
		return MODUS;
	}
	
	public static String getConfigPath() {
		return getPlugin().getDataFolder() + "";
	}
	
	
	
	public static void setLobbyLocation(Location loc) {
		Bukkit.getConsoleSender().sendMessage(prefix + "Set LobbyLocation: " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + " in " + loc.getWorld().getName() + "!"); 
		lobby = loc;
	}
	
	public static Location getLobbyLocation() {
		return lobby;
	}
	
	
	
	
	
	public static void setDeathGameLocation(Location loc) {
		Bukkit.getConsoleSender().sendMessage(prefix + "Set DeathgameLocation: " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + " in " + loc.getWorld().getName() + "!");  
		deathGame = loc;
	}
	
	public static Location getDeathGameLocation() {
		return deathGame;
	}
	
	
	private void createWorld() {
		World lobby = Bukkit.createWorld(new WorldCreator("lobby"));
	}
	
	
}
