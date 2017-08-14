package me.otisdiver.animamorphacandy;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import net.md_5.bungee.api.ChatColor;

public class Candy {

	private Material material;
	private String itemName;
	private String permission;
	private DisguiseType disguiseType;
	private int duration;

	private MobDisguise disguise;

	public Candy(Material material, String itemName, String permission, DisguiseType disguiseType, int duration) {
		this.material = material;
		this.itemName = itemName;
		this.permission = permission;
		this.disguiseType = disguiseType;
		this.duration = duration;
	}

	/**
	 * Activates the Candy's disguise for the player for the configured
	 * duration.
	 * 
	 * @param player who to disguise
	 */
	public void disguise(JavaPlugin plugin, Player player) {

		// create & save disguise if not already done
		if (disguise == null) {
			disguise = new MobDisguise(disguiseType);
		}

		// apply disguise
		DisguiseAPI.disguiseToAll(player, disguise);
		// in [duration] seconds, remove the disguise
		Bukkit.getScheduler().runTaskLater(plugin, () -> {
			// if the disguise is still active, remove it
			Disguise currentDisguise = DisguiseAPI.getDisguise(player);
			if (currentDisguise != null && currentDisguise.equals(disguise))
				DisguiseAPI.undisguiseToAll(player);
		}, duration * 20);
	}

	public boolean isItem(ItemStack it) {

		// if the item is the wrong material = false
		if (!it.getType().equals(material))
			return false;

		// if the item doesn't have the correct name = false
		String itemName = it.getItemMeta().getDisplayName();
		itemName = ChatColor.stripColor(itemName);
		if (!itemName.equals(this.itemName))
			return false;

		// item has correct material/name
		return true;
	}

	public boolean hasPermission(Player player) {
		return player.hasPermission(permission);
	}

	public Material getMaterial() {
		return material;
	}

	public String getItemName() {
		return itemName;
	}

	public String getPermission() {
		return permission;
	}

	public String getDisguise() {
		return disguiseType;
	}

	public int getDuration() {
		return duration;
	}

}
