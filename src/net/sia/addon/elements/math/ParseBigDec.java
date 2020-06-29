package net.sia.addon.elements.math;

import java.math.BigDecimal;

import org.bukkit.event.Event;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ParseBigDec extends SimpleExpression<BigDecimal> {
	private Expression<String> ex_str;

	@Override
	public Class<? extends BigDecimal> getReturnType() {
		return BigDecimal.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		ex_str = (Expression<String>)arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected BigDecimal[] get(Event arg0) {
		String str = (String)ex_str.getSingle(arg0);
		BigDecimal bInteger = new BigDecimal(str);
		return new BigDecimal[] { bInteger };
	}

}
