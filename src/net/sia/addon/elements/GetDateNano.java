package net.sia.addon.elements;

import java.util.concurrent.TimeUnit;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class GetDateNano extends SimpleExpression<Long> {
	private Expression<Long> ex_ns;
	public Class<? extends Long> getReturnType() {
		return Long.class;
	}

	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
		this.ex_ns = (Expression<Long>) exprs[0];
		return true;
	}

	public String toString(@Nullable Event event, boolean debug) {
		return "test";
	}

	@Nullable
	protected Long[] get(Event event) {
		Long ns = (Long)this.ex_ns.getSingle(event);
		Long ms = TimeUnit.SECONDS.convert(ns, TimeUnit.NANOSECONDS);
		return new Long[] { ms };
		
	}
}