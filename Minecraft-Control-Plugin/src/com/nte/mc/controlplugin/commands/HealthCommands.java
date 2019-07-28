package com.nte.mc.controlplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealthCommands {
	
	public static boolean heal(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("mcp.health.heal")) {
				if(args.length == 0) {
					p.setHealth(20.0);
					return true;
				} else if (args.length == 1) {
					for(Player t : Bukkit.getServer().getOnlinePlayers()) {
						if(args[0].equals(t.getDisplayName())) {
							t.setHealth(20.0);
							return true;
						} else {
							CommandRooter.sendPlayerNotFoundMsg(p);
							return true;
						}
					}
				} else {
					return false;
				}
			} else {
				CommandRooter.sendNoPermissionMsg(p);
				return true;
			}
		} else {
			CommandRooter.sendPlayerOnlyCmdMsg();
			return true;
		}
		return false;
	}

}
