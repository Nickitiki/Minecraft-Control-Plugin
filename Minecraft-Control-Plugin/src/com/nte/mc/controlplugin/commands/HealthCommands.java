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
					p.setHealth(p.getMaxHealth());
					return true;
				} else if (args.length == 1) {
					for(Player t : Bukkit.getServer().getOnlinePlayers()) {
						if(args[0].equalsIgnoreCase(t.getDisplayName())) {
							t.setHealth(t.getMaxHealth());
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
	
	public static boolean setMaxHealth(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("mcp.health.maxhealth")) {
				if(args.length == 0) {
					return false;
				} else if (args.length == 1) {
					try {
						p.setMaxHealth(Double.valueOf(args[0]));
						return true;
					} catch (Exception e) {
						CommandRooter.sendSyntaxErrorMsg(p);
						return true;
					}
				} else if (args.length == 2) {
					for(Player t : Bukkit.getServer().getOnlinePlayers()) {
						if(t.getDisplayName().equalsIgnoreCase(args[0])) {
							t.setMaxHealth(Double.valueOf(args[1]));
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
