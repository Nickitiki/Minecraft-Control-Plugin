package com.nte.mc.controlplugin.main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.nte.mc.controlplugin.spawnprotection.SpwanprotectionEventlistener;

public class Main extends JavaPlugin{
	
	public FileConfiguration config = this.getConfig();

	@Override
	public void onEnable() {
		System.out.println("[ControlPlugin] Enabled successfully");
		this.saveDefaultConfig();
		
		eventRegister();
	}
	
	public void eventRegister() {
		this.getServer().getPluginManager().registerEvents(new SpwanprotectionEventlistener(this), this);
	}
	
	
	@Override
	public void onDisable() {
		System.out.println("[ControlPlugin] Disabled successfully");
	}
}
