package com.nte.mc.controlplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.nte.mc.controlplugin.main.Main;

public class MonitoringCommands {
	
	private static int ticks;
	private static long starttime;
	private static int taskID = 0;

	public static boolean mem(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("mcp.mem")) {
				if(args.length == 0) {
					int mb = 1024*1024;
					Runtime runtime = Runtime.getRuntime();
					p.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +  "Monitoring:");
					p.sendMessage(ChatColor.AQUA + "Used memory: " + ((runtime.totalMemory() - runtime.freeMemory())/mb) + " mb");
					p.sendMessage(ChatColor.AQUA + "Free memory: " + runtime.freeMemory()/mb + " mb");
					p.sendMessage(ChatColor.AQUA + "Max. available memory: " + runtime.totalMemory()/mb + " mb");
					p.sendMessage(ChatColor.AQUA + "Total VRM memory: " + runtime.maxMemory()/mb + " mb");
					return true;
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
	}
	
	public static boolean tps(Main main, CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("mcp.tps")) {
				p.sendMessage(ChatColor.AQUA + "Calculating tps, please wait!");
				ticks = 0;
				starttime = System.currentTimeMillis();
				taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
					
					@Override
					public void run() {
						if(System.currentTimeMillis() < (1000 + starttime)) {
							ticks++;
						} else {
							p.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "TPS statistics");
							p.sendMessage(ChatColor.AQUA + "The current tps: " + (ticks/((System.currentTimeMillis()-starttime)/1000)));
							p.sendMessage(ChatColor.AQUA + "Time sequence: " + (System.currentTimeMillis()-starttime));
							Bukkit.getScheduler().cancelTask(taskID);
						}
					}
				}, 0, 1);
				return true;
			} else {
				CommandRooter.sendNoPermissionMsg(p);
				return true;
			}
		} else {
			System.out.println(ChatColor.AQUA + "Calculating tps, please wait!");
			ticks = 0;
			starttime = System.currentTimeMillis();
			taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
				
				@Override
				public void run() {
					if(System.currentTimeMillis() < (1000 + starttime)) {
						ticks++;
					} else {
						System.out.println(ChatColor.YELLOW + "" + ChatColor.BOLD + "TPS statistics");
						System.out.println(ChatColor.AQUA + "The current tps: " + (ticks/((System.currentTimeMillis()-starttime)/1000)));
						System.out.println(ChatColor.AQUA + "Time sequence: " + (System.currentTimeMillis()-starttime));
						Bukkit.getScheduler().cancelTask(taskID);
					}
				}
			}, 0, 1);
			return true;
		}
	}
}
