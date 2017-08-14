package me.otisdiver.animamorphacandy;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;

public class ConfigManager {

	private JavaPlugin plugin;
	private Configuration config;
	private ArrayList<Candy> candies;

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

	private void cacheCandies() {

		// get all configured candies
		ConfigurationSection candiesConfig = config.getConfigurationSection("candies");
		Set<String> candySections = candiesConfig.getKeys(false);
		for (String candySectionName : candySections) {
			ConfigurationSection candySection = candiesConfig.getConfigurationSection(candySectionName);

			// get the values & validate them
			String materialString = candySection.getString("material");
			if (!isStringValid(materialString)) {
				printError("Could not enable candy! Empty material setting in candy '" + candySectionName + "'");
				continue;
			}
			Material material = Material.valueOf(materialString);
			if (material.isBlock()) {
				printError("Could not enable candy! Found a block material in candy '" + candySectionName + "'");
				continue;
			}

			String itemName = candySection.getString("item_name");
			// optional value
			if (!isStringValid(itemName)) {
				itemName = null;
			}

			String permission = candySection.getString("permission");
			// optional value
			if (!isStringValid(permission)) {
				permission = null;
			}

			String disguiseString = candySection.getString("disguise");
			if (!isStringValid(disguiseString)) {
				printError("Could not enable candy! Empty disguise setting in candy '" + candySectionName + "'");
				continue;
			}
			DisguiseType disguise = DisguiseType.valueOf(disguiseString);
			if (disguise == null) {
				printError("Could not enable candy! Invalid disguise type in candy '" + candySectionName + "'");
				continue;
			}

			int duration = candySection.getInt("duration");
			if (duration == 0) {
				printError("Could not enable candy! Invalid (or 0) duration in candy '" + candySectionName + "'");
				continue;
			}

			// create the candy
			Candy candy = new Candy(material, itemName, permission, disguise, duration);
			candies.add(candy);
		}
	}

	public ArrayList<Candy> getCandies() {
		return candies;
	}

	public Configuration getConfig() {
		return config;
	}

	private boolean isStringValid(String string) {
		return !(string == null || string.isEmpty() || string.matches("(\\w)*"));
	}

	private void printError(String error) {
		Bukkit.getLogger().warning("[Animamorphacandy] " + error);
	}
}
