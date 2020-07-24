package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.sia.addon.Main;

public class GInfo extends SimpleExpression<Long> {
	Expression<Long> ex_mode;

	@Override
	public Class<? extends Long> getReturnType() {
		return Long.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		ex_mode = (Expression<Long>)arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected Long[] get(Event arg0) {
		long mode = (long)ex_mode.getSingle(arg0);
		int i = Math.toIntExact(mode);
		long lword = Main.ginfo.get(i).longValue();
		return new Long[] { lword };
	}

}
