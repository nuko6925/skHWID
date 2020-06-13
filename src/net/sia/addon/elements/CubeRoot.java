package net.sia.addon.elements;

import org.bukkit.event.Event;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class CubeRoot extends SimpleExpression<Double> {
	private Expression<Number> ex_num;

	@Override
	public Class<? extends Double> getReturnType() {
		return Double.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.ex_num = (Expression<Number>) arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected Double[] get(Event arg0) {
		Number number = (Number)this.ex_num.getSingle(arg0);
		try {
			return new Double[] { Math.cbrt(number.doubleValue()) };
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;
	}

}
