package net.sia.addon.elements;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class GetDate extends SimpleExpression<String> {
	private Expression<Long> exp_millis;
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
		this.exp_millis = (Expression<Long>) exprs[0];
		return true;
	}

	public String toString(@Nullable Event event, boolean debug) {
		return "test";
	}

	@Nullable
	protected String[] get(Event event) {
		Long millis = (Long)this.exp_millis.getSingle(event);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date resultdate = new Date(millis);
		return new String[] { sdf.format(resultdate) };
		
	}
}