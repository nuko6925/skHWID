package net.sia.addon.elements;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class Stepping extends SimpleExpression<String> {
	String uptime = "";
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	public boolean isSingle() {
		return true;
	}

	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
		return true;
	}

	public String toString(@Nullable Event event, boolean debug) {
		return "test";
	}

	@Nullable
	protected String[] get(Event event) {
        try {
        	uptime = System.getenv("PROCESSOR_IDENTIFIER");
        	return new String[] {uptime};
        } catch (Exception err) {
        	err.printStackTrace();
        }
		return null;
		}
	}