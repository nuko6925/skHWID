package net.sia.addon.elements;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class BorderSize extends SimpleExpression<String> {
	private Expression<String> exp_world;
	String size = "";
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
		this.exp_world = (Expression<String>) exprs[0];
		return true;
	}

	public String toString(@Nullable Event event, boolean debug) {
		return "test";
	}

	@Nullable
	protected String[] get(Event event) {
		String world = (String)this.exp_world.getSingle(event);
		double B = Bukkit.getServer().getWorld(world).getWorldBorder().getSize();
		int wint = (int) B;
        try {
        	size = String.valueOf(wint);
        	return new String[] {size};
        } catch (Exception err) {
        	err.printStackTrace();
        }
		return null;
		}
	}