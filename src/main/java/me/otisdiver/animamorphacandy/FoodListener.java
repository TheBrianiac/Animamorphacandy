package me.otisdiver.animamorphacandy;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class FoodListener implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public void onInteract(PlayerInteractEvent e) {

		Material mat = e.getItem().getType();
		if (mat.isEdible()) {
			eat(e.getPlayer(), mat);
		}
	}

	// TODO listen to PacketPlayUseItem events (to monitor players clicking air)

	private void eat(Player player, Material mat) {
		// TODO read config, give disguise
	}

}
