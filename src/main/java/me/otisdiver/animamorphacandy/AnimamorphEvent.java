package me.otisdiver.animamorphacandy;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class AnimamorphEvent extends Event implements Cancellable {

	private static final HandlerList HANDLERS = new HandlerList();

	private boolean cancelled = false;

	private Player player;
	private ItemStack item;

	public AnimamorphEvent(Player player, ItemStack item) {
		this.player = player;
		this.item = item;
	}

	public Player getPlayer() {
		return player;
	}

	public ItemStack getItem() {
		return item;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	/**
	 * Returns true if a Listener cancelled the event.
	 */
	public boolean isCancelled() {
		return cancelled;
	}

	/**
	 * Sets whether this event is cancelled.
	 */
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	/**
	 * Sets this event to cancelled. Shortcut for #setCancelled(true).
	 */
	public void cancel() {
		setCancelled(true);
	}

}
