package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class Flight extends Effect {
	private Expression<Player> exp_player;
	private Expression<Boolean> exp_boolean;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.exp_player = (Expression<Player>) arg0[0];
		this.exp_boolean = (Expression<Boolean>) arg0[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event arg0) {
		Player player = (Player)this.exp_player.getSingle(arg0);
		CraftPlayer p = (CraftPlayer)player;
		Boolean b = (Boolean)this.exp_boolean.getSingle(arg0);
		try {
			if (b == true) {
				p.setAllowFlight(true);
			} else {
				p.setAllowFlight(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
