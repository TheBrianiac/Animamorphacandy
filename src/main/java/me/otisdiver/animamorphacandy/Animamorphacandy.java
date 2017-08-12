package me.otisdiver.animamorphacandy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Animamorphacandy is a project that allows server admins to set certain foods
 * to cause players to become disguised with Lib's Disguises.
 * 
 * @author Brian Schultz, brianschultz.net
 */
public class Animamorphacandy extends JavaPlugin {

	@Override
	public void onEnable() {

		// initialize the config manager, which provides convenience access to
		// the config... MUST be the first thing run
		ConfigManager.init(this);

		// register the FoodListener, which runs logic when a player eats food
		Bukkit.getPluginManager().registerEvents(new FoodListener(), this);
	}

}
