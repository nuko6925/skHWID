package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class Flight2 extends SimpleExpression<Boolean> {
	private Expression<Player> exp_player;

	@Override
	public Class<? extends Boolean> getReturnType() {
		return null;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.exp_player = (Expression<Player>) arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected Boolean[] get(Event arg0) {
		Player player = (Player)this.exp_player.getSingle(arg0);
		CraftPlayer p = (CraftPlayer)player;
		try {
			Boolean b = p.getAllowFlight();
			if (b == true) {
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
