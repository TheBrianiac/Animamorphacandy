package me.otisdiver.animamorphacandy;

import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

/**
 * Animamorphacandy is a project that allows server admins to set certain foods
 * to cause players to become disguised with Lib's Disguises.
 * 
 * @author Brian Schultz, brianschultz.net
 */
public class Animamorphacandy extends JavaPlugin {

	private ProtocolManager protocolManager;

	@Override
	public void onEnable() {

		// initialize the config manager, which provides convenience access to
		// the config... MUST be the first thing run
		ConfigManager.init(this);

		// initialize protocol manager, which intercepts packets
		protocolManager = ProtocolLibrary.getProtocolManager();

		// register the FoodListener, which runs logic when a player eats food
		FoodListener foodListener = new FoodListener(this);
		protocolManager.addPacketListener(foodListener);
	}

}
