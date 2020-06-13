package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class Plugin extends SimpleExpression<Boolean> {
	private Expression<String> ex_pl;
	@Override
	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.ex_pl = (Expression<String>) arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected Boolean[] get(Event arg0) {
		String plugin = (String)this.ex_pl.getSingle(arg0);
		try {
			if (Bukkit.getPluginManager().getPlugin(plugin).isEnabled()) {
				return new Boolean[] {true};
			} else {
				return new Boolean[] {false};
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
