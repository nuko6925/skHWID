package net.sia.addon.elements.math;

import java.math.BigInteger;

import org.bukkit.event.Event;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class Fact extends SimpleExpression<BigInteger> {
	private Expression<Long> ex_long;

	@Override
	public Class<? extends BigInteger> getReturnType() {
		return BigInteger.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.ex_long = (Expression<Long>) arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected BigInteger[] get(Event arg0) {
		long l = (long)this.ex_long.getSingle(arg0);
		int n = (int)l;
		BigInteger r = BigInteger.ONE;
		for (int i = 2; i <= n; i++)
			r = r.multiply(BigInteger.valueOf(i));
		return new BigInteger[] { r };
	}
	

}
