package de.agent117.TVD.game;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.agent117.TVD.main.main;

public class teamManager implements IText{
	public static int oneTeam = 1;
	public static int twoTeam = 2;
	
	private static HashMap<Player, Teams> teams = new HashMap<Player, Teams>();
		
	public static void addPlayerToTeam(Player p, Teams team, boolean check) {
		if(countPlayersInTeam(team) < getMaxTeamSize() && check) {
			if(containsTeam(p)) {
				teams.remove(p);
			}
			
			teams.put(p, team);
			p.sendMessage(changeTeam.replaceAll("%team%", team.getName()));
		} else {
			
			if(check) {
				p.sendMessage(fullTeam);
			} else {
				teams.put(p, team);
				p.sendMessage(changeTeam.replaceAll("%team%", team.getName()));
			}
			
		}
	}
	
	public static boolean arePlayersInTeam(Player p1, Player p2) {
		if(teams.get(p1).equals(teams.get(p2))) {
			return true;
		} 
		
		return false;
	}
	
	public static void setLastPlayerInTeams() {
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			
			int count = 0;
			while(!containsTeam(p)) {
				Teams t = Teams.getTeamList()[count];
				if(countPlayersInTeam(t) < getMaxTeamSize()) {
					addPlayerToTeam(p, t, false);
				}
				
				count++;
				
				
			}
				
			
			
		}
	}
	
	public static Teams getTeamFromPlayer(Player p) {	
		return teams.get(p);
	}
	
	public static int getMaxTeamSize() {
		if(main.getModus() == oneTeam) {
			return 1;
		} else {
			return 2;
		}
	}
	
	public static int countPlayersInTeam(Teams team) {
		int count = 0;
		for(Teams t : teams.values()) {
			if(t.equals(team)) {
				count++;
			}
		}
		
		return count;
	}
	
	public static Player[] getPlayerInTeam(Teams team) {
		int slot = 0;
		Player[] players = new Player[2];
		for(Player p : teams.keySet()) {
			if(teams.get(p).equals(team)) {
				players[slot++] = p;
			}
		}
		
		return players;
	}
	
	
	public static boolean containsTeam(Player p) {
		if(teams.containsKey(p)) {
			return true;
		}
		
		return false;
	}
	
}
