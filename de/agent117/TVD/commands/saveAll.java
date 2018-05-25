package de.agent117.TVD.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.agent117.TVD.game.IText;
import de.agent117.TVD.main.main;
import de.agent117.TVD.main.saveInformations;

public class saveAll implements IText, CommandExecutor {
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			saveInformations.saveDeathGameLoc(main.getDeathGameLocation());
			saveInformations.saveLobbyLoc(main.getLobbyLocation());
			return true;
		} else {
			System.out.println("Player only!");
		}
		
		return false;
	}

}


