package de.agent117.TVD.mechanic;

public interface IPath {
	//chestConfig
	String maxChestItems = "Config.maxChestItems";
	String chestItemListPath = "Config.chestItemList";
	
	//SupplyConfig
	String maxSupplyItems = "Config.maxSupplyItems";
	String supplyItemListPath = "Config.supplyItemList";
	
	//standart Config
	String teamModusPath = "Config.TeamModus";
	
	
	String spawnSupplyChestPath = "Config.Text.spawnSupplyChest";
	String refillChestPath = "Config.Text.refillChest";	
	
	String changeTeamPath = "Config.Text.changeTeam";
	String fullTeamPath = "Config.Text.fullTeam";
	
	String playerWinPath = "Config.Text.playerWin";
	String startDeathgamePath = "Config.Text.startDeathgame";
	

	
	String saveAllPath = "Config.Text.saveAll";
	String setLobbyLocPath = "Config.Text.setNewLobbyLoc";
	String setDeathGameLocPath = "Config.Text.setNewDeathGameLoc";
	
	
	
	
	String teamJoinItemPath = "Config.Items.teamJoin";
	
	
	String lobbyLocation = "Config.Locations.lobby";			// world, x, y, z, yaw, pitch
	String deathGameLocation = "Config.Locations.deathGame";	// world, x, y, z, yaw, pitch
}
