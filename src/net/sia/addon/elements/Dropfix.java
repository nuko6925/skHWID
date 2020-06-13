package net.sia.addon.elements;

import org.bukkit.event.Event;

import org.bukkit.Location;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Entity;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class Dropfix extends Effect {
	private Expression<ItemType> exp_item;
	private Expression<Location> exp_loc;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.exp_item = (Expression<ItemType>) arg0[0];
		this.exp_loc = (Expression<Location>) arg0[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event arg0) {
		Location loc = (Location)this.exp_loc.getSingle(arg0);
		ItemType item = (ItemType)this.exp_item.getSingle(arg0);
		Entity entity = loc.getWorld().dropItemNaturally(loc, item.getRandom());
		entity.setVelocity(entity.getVelocity().zero());
		
	}
	

}
