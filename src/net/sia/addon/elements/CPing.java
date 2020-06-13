package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class CPing extends SimpleExpression<Integer> {
	Expression<Player> ex_player;

	@Override
	public Class<? extends Integer> getReturnType() {
		return Integer.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.ex_player = (Expression<Player>)arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected Integer[] get(Event arg0) {
		Player p = (Player)this.ex_player.getSingle(arg0);
		try {
			Object entityPlayer = p.getClass().getMethod("getHandle").invoke(p);
			int ping = (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
			return new Integer[] {ping};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
