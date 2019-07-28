package com.nte.mc.controlplugin.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.nte.mc.controlplugin.commands.HealthCommands;
import com.nte.mc.controlplugin.commands.Mcp;
import com.nte.mc.controlplugin.spawnprotection.SpwanprotectionEventlistener;

public class Main extends JavaPlugin{
	
	public FileConfiguration config = this.getConfig();

	@Override
	public void onEnable() {
		Variables.config = config;
		System.out.println("[ControlPlugin] Enabled successfully");
		this.saveDefaultConfig();
		
		eventRegister();
	}
	
	public void eventRegister() {
		this.getServer().getPluginManager().registerEvents(new SpwanprotectionEventlistener(this), this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("mcp")) {
			new Mcp(sender, cmd, label, args);
			return true;
		} else if (cmd.getName().equalsIgnoreCase("heal")) {
			return HealthCommands.heal(sender, cmd, label, args);
		}
		
	return false;
	}
	
	@Override
	public void onDisable() {
		System.out.println("[ControlPlugin] Disabled successfully");
	}
}
