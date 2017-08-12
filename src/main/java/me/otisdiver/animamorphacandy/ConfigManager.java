package me.otisdiver.animamorphacandy;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {

	private JavaPlugin plugin;
	private Configuration config;

	// Singleton structure...

	private static ConfigManager instance;

	private ConfigManager(JavaPlugin plugin) {
		this.plugin = plugin;
		config = plugin.getConfig();
	}

	public static ConfigManager init(JavaPlugin plugin) {
		instance = new ConfigManager(plugin);
		return instance;
	}

	public static ConfigManager getInstance() {
		return instance;
	}

	// ... singleton structure.

	public Configuration getConfig() {
		return config;
	}

	// TODO utility methods
}
