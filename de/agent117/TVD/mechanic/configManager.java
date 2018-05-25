package de.agent117.TVD.mechanic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.bukkit.configuration.file.YamlConfiguration;

import de.agent117.TVD.main.main;

public class configManager {
	
	private static File chestConfig = new File(main.getConfigPath() + File.separatorChar + "chestdrop.yml");
	private static File supplyConfig = new File(main.getConfigPath() + File.separatorChar + "supplydrop.yml");
	private static File standardConfig = new File(main.getConfigPath() + File.separatorChar + "config.yml");
	
	public static YamlConfiguration getChestConfig()  {
		
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(chestConfig);
		try {
			cfg.save(chestConfig);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cfg;
	}
	
	public static YamlConfiguration getSupplyConfig() {
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(supplyConfig);
		try {
			cfg.save(supplyConfig);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cfg;
	}
	
	public static YamlConfiguration getStandardConfig() {
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(standardConfig);
		
		return cfg;
	}
	
	
	public static void checkForConfigFiles() {	
		main.getPlugin().saveDefaultConfig();
//		checkFile(standardConfig, "config.yml");
		checkFile(chestConfig, "chestdrop.yml");
		checkFile(supplyConfig, "supplydrop.yml");
	}
	
	public static File getChestConfigFile() {
		return chestConfig;
	}

	public static File getSupplyConfigFile() {
		return supplyConfig;
	}
	
	public static File getStandardConfigFile() {
		return standardConfig;
	}
	
	private static void checkFile(File f, String path) {
		try {
			if(!f.exists()) {
				InputStream steam = main.class.getResourceAsStream("/" + path);
				
				Scanner s = new Scanner(steam);
				FileWriter fw = new FileWriter(f);

				BufferedWriter wr = new BufferedWriter(fw);
				
				while(s.hasNextLine()) {
					String line = s.nextLine() + "\n";
					wr.write(line);
				}
				

				wr.close();
				fw.close();
				s.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

} 
