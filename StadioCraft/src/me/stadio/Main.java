package me.stadio;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main plugin;
	File file = new File(getDataFolder() + File.separator + "config.yml");
	
	@Override
	public void onEnable() {
		plugin = this;	

		getLogger().info("The StadioCraft Plugin is enabled!");

		getCommands();
		
		if (!file.exists())
		{
		initializeConfig();
		}	
	}

	@Override
	public void onDisable() {
		saveConfig();
	}

	public void initializeConfig() 
	{
		this.getLogger().info("Generating config file...");
		this.getConfig().addDefault("Rage Message", "You used your Rage ability.");
		this.getConfig().addDefault("Regen Message", "You used your Regen ability.");
		this.getConfig().addDefault("Fury Message", "You used your Fury ability.");
		this.getConfig().addDefault("TNT Message", "You used your TNT command.");
		this.getConfig().addDefault("Rage CooldownTime", 600);
		this.getConfig().addDefault("Regen CooldownTime", 600);
		this.getConfig().addDefault("Fury CooldownTime", 600);
		this.getConfig().addDefault("TNT CooldownTime", 150);
		
		this.getConfig().isInt("Rage CooldownTime");
		this.getConfig().isInt("Regen CooldownTime");
		this.getConfig().isInt("Fury CooldownTime");
		this.getConfig().isInt("TNT CooldownTime");
		
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	private void getCommands() {
		getCommand("regen").setExecutor(new RegenCommand());
		getCommand("fury").setExecutor(new FuryCommand());
		getCommand("rage").setExecutor(new RageCommand());
		getCommand("tnt").setExecutor(new TNTCommand());
		getCommand("stadioreload").setExecutor(new ConfigReload());
	}

}
