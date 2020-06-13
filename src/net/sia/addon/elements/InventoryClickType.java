package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class InventoryClickType extends SimpleExpression<ClickType>{

	@Override
	public Class<? extends ClickType> getReturnType() {
		return ClickType.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		if (!ScriptLoader.isCurrentEvent(InventoryClickEvent.class)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "haha";
	}

	@Override
	@Nullable
	protected ClickType[] get(Event arg0) {
		return new ClickType[]{((InventoryClickEvent)arg0).getClick()};
	}

}
