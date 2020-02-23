package net.sia.addon.elements;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class Uptime extends SimpleExpression<String> {
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
		RuntimeMXBean jre = ManagementFactory.getRuntimeMXBean();
        try {
        	uptime = ("" + jre.getUptime());
        	return new String[] {uptime};
        } catch (Exception err) {
        	err.printStackTrace();
        }
		return null;
		}
	}