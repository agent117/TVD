package de.agent117.TVD.main;


import org.bukkit.Bukkit;
import org.bukkit.Location;

import de.agent117.TVD.mechanic.IPath;
import de.agent117.TVD.mechanic.configManager;

public class loadLocations implements IPath{
	
	
	
	
	public static void loadAll() {	
		
		main.setLobbyLocation(loadWorldLocation(lobbyLocation));
		main.setDeathGameLocation(loadWorldLocation(deathGameLocation));
	}
	
	
	// world x y z yaw pitch
	private static Location loadWorldLocation(String path) {
		String[] s = configManager.getStandardConfig().getString(path).split(main.getSplitChar());
		
		Location loc = new Location(
				Bukkit.getWorld(s[0]),		// Welt
				Double.parseDouble(s[1]),	// x
				Double.parseDouble(s[2]),	// y
				Double.parseDouble(s[3]),	// z
				Float.parseFloat(s[4]),		// yaw
				Float.parseFloat(s[5]));	// pitch
		return loc;
	}
	
}
