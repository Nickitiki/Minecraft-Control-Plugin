package com.nte.mc.controlplugin.spawnprotection;


import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.nte.mc.controlplugin.commands.CommandRooter;
import com.nte.mc.controlplugin.main.Main;
import com.nte.mc.controlplugin.main.Variables;

public class SpwanprotectionEventlistener implements Listener{
	
	Main main;
	World world = Bukkit.getServer().getWorld("world");
	private int b1x;
	private int b1y;
	private int b1z;
	private int b2x;
	private int b2y;
	private int b2z;
	private boolean dirX;
	private boolean dirY;
	private boolean dirZ;
	
	
	public SpwanprotectionEventlistener(Main main) {
		this.main = main;
		this.b1x = main.config.getInt("Spawnprotection.Block1.x");
		this.b1y = main.config.getInt("Spawnprotection.Block1.y");
		this.b1z = main.config.getInt("Spawnprotection.Block1.z");
		this.b2x = main.config.getInt("Spawnprotection.Block2.x");
		this.b2y = main.config.getInt("Spawnprotection.Block2.y");
		this.b2z = main.config.getInt("Spawnprotection.Block2.z");
	}
	
	@EventHandler
	public void playerMoveEvent(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		if(Variables.adminList.contains(p.getName())) {
			return;
		}
		
		dirX = false;
		dirY = false;
		dirZ = false;
		
		if(b1x > b2x) {
			if(p.getLocation().getBlockX() < b1x && p.getLocation().getBlockX() > b2x) {
				dirX = true;
			}
		} else {
			if(p.getLocation().getBlockX() > b1x && p.getLocation().getBlockX() < b2x) {
				dirX = true;
			}
		}
		
		if(b1y > b2y) {
			if(p.getLocation().getBlockY() < b1y && p.getLocation().getBlockY() > b2y) {
				dirY = true;
			}
		} else {
			if(p.getLocation().getBlockY() > b1y && p.getLocation().getBlockY() < b2y) {
				dirY = true;
			}
		}
		
		if(b1z > b2z) {
			if(p.getLocation().getBlockZ() < b1z && p.getLocation().getBlockZ() > b2z) {
				dirZ = true;
			}
		} else {
			if(p.getLocation().getBlockZ() > b1z && p.getLocation().getBlockZ() < b2z) {
				dirZ = true;
			}
		}
		
		if(dirX && dirY && dirZ) {
			if(!p.getGameMode().equals(GameMode.ADVENTURE)) {
				CommandRooter.sendEnteredProtectAreaMsg(p);
				p.setGameMode(GameMode.ADVENTURE);
			}
			
		} else {
			if(!p.getGameMode().equals(GameMode.SURVIVAL)) {
				CommandRooter.sendLeftProtectAreaMsg(p);
				p.setGameMode(GameMode.SURVIVAL);
			}
		}
	}
}
