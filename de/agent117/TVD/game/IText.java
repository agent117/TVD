package de.agent117.TVD.game;

import org.bukkit.ChatColor;

import de.agent117.TVD.mechanic.IPath;
import de.agent117.TVD.mechanic.configManager;

public interface IText extends IPath{
//	String spawnSupplyChest = trans("§3 Supplychest at: %x% %y% %z%");	//%x%    %y%    %z%
	String spawnSupplyChest = trans(configManager.getStandardConfig().getString(spawnSupplyChestPath));	
	
//	String refillChest = trans("§3 Chest are now full!");	
	String refillChest = trans(configManager.getStandardConfig().getString(refillChestPath));
	
//	String changeTeam = trans("§3 You are now in Team: %team%!");	// %team%	
	String changeTeam = trans(configManager.getStandardConfig().getString(changeTeamPath));
	
//	String fullTeam = trans("§3 This Team is full!");		
	String fullTeam = trans(configManager.getStandardConfig().getString(fullTeamPath));
	
	
//	String playerWin = trans("§3 The winner is: %player%!");	// %player%	
	String playerWin = trans(configManager.getStandardConfig().getString(playerWinPath));
	String startDeathgame = trans(configManager.getStandardConfig().getString(startDeathgamePath));
	
	
//	String msgAddSpawnLoc = trans("add new Spawnlocation!");	
//	String msgAddSpawnLoc = trans(configManager.getStandardConfig().getString(msgAddSpawnLocPath));
//	String msgAddLootChestLoc = trans(configManager.getStandardConfig().getString(msgAddLootChestLocPath));
	
	
	
//	String errorAddSpawnLoc = trans("To many Spawnlocations!");	
//	String errorAddSpawnLoc = trans(configManager.getStandardConfig().getString(errorAddSpawnLocPath));
//	String errorAddLootChestLoc = trans(configManager.getStandardConfig().getString(errorAddLootChestLocPath));
	
	
	
//	String msgRemoveSpawnLoc = trans("remove Spawnlocation!");	
//	String msgRemoveSpawnLoc = trans(configManager.getStandardConfig().getString(msgRemoveSpawnLocPath));
	
	
//	String errorRemoveSpawnLoc = trans("Spawnlocation not found!");	
//	String errorRemoveSpawnLoc = trans(configManager.getStandardConfig().getString(errorRemoveSpawnLocPath));
	
	
	
	
//	String setMiddleLoc = trans("set new Middel Location!");	
//	String setMiddleLoc = trans(configManager.getStandardConfig().getString(setMiddleLocPath));
	
	String saveAll = trans(configManager.getStandardConfig().getString(saveAllPath));
	
//	String setLobbyLoc = trans("set new Lobby Location!");	
	String setLobbyLoc = trans(configManager.getStandardConfig().getString(setLobbyLocPath));
	
//	String setDeathGameLoc = trans("set new Death Game Location!");	
	String setDeathGameLoc = trans(configManager.getStandardConfig().getString(setDeathGameLocPath));
	
	
	
	
	
//	String teamJoinItem = trans("§3 Teams");	
	String teamJoinItem = trans(configManager.getStandardConfig().getString(teamJoinItemPath));
	
	
	static String trans(String input) {
		return ChatColor.translateAlternateColorCodes('§', input);
	}
}
 