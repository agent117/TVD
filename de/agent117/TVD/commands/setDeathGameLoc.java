package de.agent117.TVD.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.agent117.TVD.game.IText;
import de.agent117.TVD.main.main;

public class setDeathGameLoc implements IText, CommandExecutor{

	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player)sender;
			Location loc = p.getLocation();
		
			main.setDeathGameLocation(loc);
			
			p.sendMessage(setDeathGameLoc);
			return true;
		} else {
			System.out.println("Player only!");
		}
		
		return false;
	}

}
