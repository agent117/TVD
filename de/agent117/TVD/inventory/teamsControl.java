package de.agent117.TVD.inventory;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.agent117.TVD.game.IText;
import de.agent117.TVD.game.Teams;
import de.agent117.TVD.game.teamManager;

public class teamsControl implements IText{
	
	
	public static ItemStack getMenuItem() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("");
		ItemStack i = new ItemStack(Material.BLAZE_ROD);
		ItemMeta m = i.getItemMeta();
			m.setDisplayName(teamJoinItem);
			m.setLore(lore);
			
		i.setItemMeta(m);
		return i;
	}
	
	public static ItemStack getTeamItem(Teams team, int slot) {	
		ArrayList<String> lore = new ArrayList<String>();
		ItemStack i;
		int free = teamManager.countPlayersInTeam(team);
		int max = teamManager.getMaxTeamSize();
			lore.add("Slots: " + free + "/" + max);											
			lore.add("");
		if(slot <= 12) {
			i = new ItemStack(Material.WOOL, 1, (short) 0, (byte) slot);
		} else {
			i = new ItemStack(Material.STAINED_GLASS, 1, (short) 0, (byte) (slot - 12));
		}
		ItemMeta m = i.getItemMeta();
			m.setDisplayName(team.getName());
			m.setLore(lore);
			
		i.setItemMeta(m);
		return i;
	}
	
	public static Inventory getMenuInventory() {
		int maxTeamSize = teamManager.getMaxTeamSize();
		int isize = (2/maxTeamSize * 9) + (24/maxTeamSize) + (3 * maxTeamSize);
		Inventory inv = Bukkit.createInventory(null, isize, "TVD: Team Menu");
		
		int slot = 1;
		int diff = 0;
		for(int i = 0; i < (24 / maxTeamSize); i++) {
			inv.setItem(i + diff, getTeamItem(Teams.getTeamList()[i], slot));
			slot++;
			
			if(i == 8 || i == 17) {
				diff += 9;
			}
		}
		
		return inv;
	}
	
	public static ItemStack getElytra() {
		ItemStack i = new ItemStack(Material.ELYTRA);
		
		return i;
	}
}
