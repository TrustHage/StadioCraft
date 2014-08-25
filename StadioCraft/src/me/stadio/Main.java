package me.stadio;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{

	public static Main plugin;
	
	@Override
	public void onEnable()
	{
		getLogger().info("The StadioCraft Plugin is enabled!");
		getCommand("regen").setExecutor(new RegenCommand());
		getCommand("fury").setExecutor(new FuryCommand());
		getCommand("rage").setExecutor(new RageCommand());
		getCommand("tnt").setExecutor(new TNTCommand());
		getConfig().options().copyDefaults(true);
		saveConfig();
		}
	
	@Override
	public void onDisable()
	{
		saveConfig();
	}
}
