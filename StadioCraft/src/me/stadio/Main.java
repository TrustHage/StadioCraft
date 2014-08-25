package me.stadio;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main plugin;

	@Override
	public void onEnable() {
		plugin = this;

		getLogger().info("The StadioCraft Plugin is enabled!");

		getCommands();

		initializeConfig();
	}

	@Override
	public void onDisable() {
		saveConfig();
	}

	private void initializeConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	private void getCommands() {
		getCommand("regen").setExecutor(new RegenCommand());
		getCommand("fury").setExecutor(new FuryCommand());
		getCommand("rage").setExecutor(new RageCommand());
		getCommand("tnt").setExecutor(new TNTCommand());
	}

}
