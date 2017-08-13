package me.otisdiver.animamorphacandy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListeningWhitelist;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.PacketListener;

public class FoodListener implements PacketListener {

	private JavaPlugin plugin;

	public FoodListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	/*
	 * Similar to using a PlayerInteractEvent, except that it bypasses Bukkit
	 * and interacts with the protocol. This is necessary because Bukkit ignores
	 * when a player has 'interacted' with air, so players eating candy without
	 * looking at a block would end up eating the food normally.
	 */
	public void onPacketReceiving(PacketEvent pe) {

		// get packet data
		if (pe.getPacketType().equals(PacketType.Play.Client.USE_ITEM)) {
			Player player = pe.getPlayer();
			ItemStack item = player.getInventory().getItemInMainHand();

			// TODO check in config if item is a configured candy

			// call an event, stop if it's cancelled
			AnimamorphEvent event = new AnimamorphEvent(player, item);
			Bukkit.getPluginManager().callEvent(event);
			if (event.isCancelled()) {
				return;
			}
		}
	}

	public ListeningWhitelist getReceivingWhitelist() {
		// TODO Auto-generated method stub
		return null;
	}

	public Plugin getPlugin() {
		return plugin;
	}

	// required by PacketListener interface, we don't need it
	public void onPacketSending(PacketEvent arg0) {
	}

	public ListeningWhitelist getSendingWhitelist() {
		return null;
	}

}
