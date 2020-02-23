package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class Yes extends Condition {
	private Expression<Object> exp_is;
	private Expression<Object> exp_it;
	

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.exp_is = (Expression<Object>) arg0[0];
		this.exp_it = (Expression<Object>) arg0[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	public boolean check(Event arg0) {
		Object is = (Object)this.exp_is.getSingle(arg0);
		Object it = (Object)this.exp_it.getSingle(arg0);
		try {
			if (is == it) {
				return true;
			} else {
				return false;
			}
		} catch (Exception err) {
			err.printStackTrace();
		}
		return false;
	}
	

}
