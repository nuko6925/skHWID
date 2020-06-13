package net.sia.addon.elements;

import java.lang.management.ManagementFactory;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import com.sun.management.OperatingSystemMXBean;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class Cpuload2 extends SimpleExpression<Double> {
	public Class<? extends Double> getReturnType() {
		return Double.class;
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
	protected Double[] get(Event event) {
		OperatingSystemMXBean osMx = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		Double t = osMx.getSystemCpuLoad() * 100;
        try {
        	return new Double[] {t};
        } catch (Exception err) {
        	err.printStackTrace();
        }
		return null;
		}
	}