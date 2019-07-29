package com.nte.mc.controlplugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.nte.mc.controlplugin.main.Variables;

public class CommandRooter{

	public static void sendNoPermissionMsg(Player p) {
		p.sendMessage(ChatColor.RED + Variables.config.getString("Messages.No-permission"));
	}
	public static void sendSuccessfulLoggedInMsg(Player p) {
		p.sendMessage(ChatColor.GREEN + Variables.config.getString("Messages.Successfully-logged-in"));
	}
	public static void sendSuccessfulLoggedOutMsg(Player p) {
		p.sendMessage(ChatColor.GREEN + Variables.config.getString("Messages.Successfully-logged-out"));
	}
	public static void sendAlredyLoggedInMsg(Player p) {
		p.sendMessage(ChatColor.RED + Variables.config.getString("Messages.Already-logged-in"));
	}
	public static void sendAlredyLoggedOutMsg(Player p) {
		p.sendMessage(ChatColor.RED + Variables.config.getString("Messages.Already-logged-out"));
	}
	public static void sendPlayerNotFoundMsg(Player p) {
		p.sendMessage(ChatColor.RED + Variables.config.getString("Messages.Player-not-found"));
	}
	public static void sendSyntaxErrorMsg(Player p) {
		p.sendMessage(ChatColor.RED + Variables.config.getString("Messages.Syntax-error"));
	}
	public static void sendPlayerOnlyCmdMsg() {
		System.out.println(Variables.config.getString("Messages.Player-only-command"));
	}
}
