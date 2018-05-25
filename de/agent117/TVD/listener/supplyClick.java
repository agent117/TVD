package de.agent117.TVD.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.agent117.TVD.game.IText;
import de.agent117.TVD.main.main;
import de.agent117.TVD.mechanic.IPath;
import de.agent117.TVD.mechanic.configManager;

public class supplyClick implements IPath, IText{
	
	private static HashMap<Location, Inventory> data = new HashMap<Location, Inventory>();
	
	
	public static boolean onClick(Player p, Location loc) {
		if(containsLocation(loc)) {
			Inventory inv = getInventory(loc);
			p.openInventory(inv);
			return true;
		}
		
		
		return false;
	}
	
	
	public static void createInventories() {
		ArrayList<Location> locs = new ArrayList<Location>();
		YamlConfiguration cfg = configManager.getSupplyConfig();
		int count = 0;
		ItemStack[] items;
		int[] chances;
		Random rnd = new Random();
			
		locs.addAll(data.keySet());
		data.clear();
		
		// ID, sub-ID, Anzahl, Chance in %
		
		List<String> itemList = cfg.getStringList(supplyItemListPath);
		items = new ItemStack[itemList.size()];
		chances = new int[itemList.size()];
		
		for(String s : itemList) {
			String[] meta = s.split(main.getSplitChar());
			
			ItemStack item = new ItemStack(Integer.parseInt(meta[0]), Integer.parseInt(meta[2]), (short)0, Byte.parseByte(meta[1]));
			
			items[count] = item;
			chances[count] = Integer.parseInt(meta[3]);
			count ++;
		}
		
		
		for(Location l : locs) {
			Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST);
			for(int i = 0; i < configManager.getSupplyConfig().getInt(maxSupplyItems); i++) {
				int rndInt = rnd.nextInt(items.length);
				
				ItemStack rndItem = items[rndInt];
				int chance = chances[rndInt];
				
				if(chance >= rnd.nextInt(99)+1) {
					inv.setItem(rnd.nextInt(inv.getSize()), rndItem);
				} 
			}
			addLocation(l, inv);
		}
		
	}
	
	
	public static boolean containsLocation(Location loc) {
		if(data.containsKey(loc)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void removeLocation(Location loc) {
		data.remove(loc);
	}
	
	public static void addLocation(Location loc) {
		data.put(loc, null);
	}
	
	public static Inventory getInventory(Location loc) {
		return data.get(loc);
	}
	
	private static void addLocation(Location loc, Inventory inv) {
		data.put(loc, inv);
	}
	
}
