package net.sia.addon.elements.math;

import org.bukkit.event.Event;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class Napier extends SimpleExpression<Double> {

	@Override
	public Class<? extends Double> getReturnType() {
		return Double.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		return false;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected Double[] get(Event arg0) {
		return new Double[] { 2.71828182846 };
	}

}
