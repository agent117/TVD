package de.agent117.TVD.game;

public enum Teams {
	
	team0 (""),
	team1 ("Team 1"),
	team2 ("Team 2"),
	team3 ("Team 3"),
	team4 ("Team 4"),
	team5 ("Team 5"),
	team6 ("Team 6"),
	team7 ("Team 7"),
	team8 ("Team 8"),
	team9 ("Team 9"),
	team10 ("Team 10"),
	team11 ("Team 11"),
	team12 ("Team 12"),
	team13 ("Team 13"),
	team14 ("Team 14"),
	team15 ("Team 15"),
	team16 ("Team 16"),
	team17 ("Team 17"),
	team18 ("Team 18"),
	team19 ("Team 19"),
	team20 ("Team 20"),
	team21 ("Team 21"),
	team22 ("Team 22"),
	team23 ("Team 23"),
	team24 ("Team 24");
	
	private String name;
	private static Teams[] teamList = new Teams[24];
	
	Teams(String name) {
		this.name= name;
	}
	
	public String getName() {
		return name;
	}
	
	public static Teams getTeam(String team) {
		for(Teams t : getTeamList()) {
			if(t.getName().equals(team)) {
				return t;
			}
		}
		
		return null;
	}
	
	public static Teams[] getTeamList() {
		teamList[0] = team1;
		teamList[1] = team2;
		teamList[2] = team3;
		teamList[3] = team4;
		teamList[4] = team5;
		teamList[5] = team6;
		teamList[6] = team7;
		teamList[7] = team8;
		teamList[8] = team9;
		teamList[9] = team10;
		teamList[10] = team11;
		teamList[11] = team12;
		teamList[12] = team13;
		teamList[13] = team14;
		teamList[14] = team15;
		teamList[15] = team16;
		teamList[16] = team17;
		teamList[17] = team18;
		teamList[18] = team19;
		teamList[19] = team20;
		teamList[20] = team21;
		teamList[21] = team22;
		teamList[22] = team23;
		teamList[23] = team24;
		
		return teamList;
	}
	
	
}
