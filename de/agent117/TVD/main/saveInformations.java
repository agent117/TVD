package de.agent117.TVD.main;

import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import de.agent117.TVD.mechanic.IPath;
import de.agent117.TVD.mechanic.configManager;

public class saveInformations implements IPath{

	
	public static void saveLobbyLoc(Location l) {
		YamlConfiguration cfg = configManager.getStandardConfig();
		String s = l.getWorld().getName() + main.getSplitChar() + l.getX() + main.getSplitChar() + l.getY() + main.getSplitChar() + l.getZ() + main.getSplitChar() + l.getYaw() + main.getSplitChar() + l.getPitch();
		cfg.set(lobbyLocation, s);
		
		save(cfg);
	}
	
	
	public static void saveDeathGameLoc(Location l) {
		YamlConfiguration cfg = configManager.getStandardConfig();
		String s = l.getWorld().getName() + main.getSplitChar() + l.getX() + main.getSplitChar() + l.getY() + main.getSplitChar() + l.getZ() + main.getSplitChar() + l.getYaw() + main.getSplitChar() + l.getPitch();
		cfg.set(deathGameLocation, s);
		save(cfg);
	}
	
	
	private static void save(YamlConfiguration cfg) {
		try {
			cfg.save(configManager.getStandardConfigFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
