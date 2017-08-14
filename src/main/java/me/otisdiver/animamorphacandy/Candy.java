package me.otisdiver.animamorphacandy;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import net.md_5.bungee.api.ChatColor;

public class Candy {

	private Material material;
	private String itemName;
	private String permission;
	private DisguiseType disguise;
	private int duration;

	public Candy(Material material, String itemName, String permission, DisguiseType disguise, int duration) {
		this.material = material;
		this.itemName = itemName;
		this.permission = permission;
		this.disguise = disguise;
		this.duration = duration;
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
		return disguise;
	}

	public int getDuration() {
		return duration;
	}

}
