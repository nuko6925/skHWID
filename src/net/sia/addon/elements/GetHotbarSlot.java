package net.sia.addon.elements;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class GetHotbarSlot extends SimpleExpression<Long> {
	Expression<Player> ex_p;

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
		this.ex_p = (Expression<Player>)arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected Long[] get(Event arg0) {
		Player player = (Player)this.ex_p.getSingle(arg0);
		int i = player.getInventory().getHeldItemSlot();
		long l = (long)i;
		return new Long[] { l };
	}

}
