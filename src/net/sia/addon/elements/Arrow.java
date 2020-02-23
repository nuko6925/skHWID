package net.sia.addon.elements;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class Arrow extends Effect {
	private Expression<Player> player;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.player = (Expression<Player>) arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event arg0) {
		Player p = (Player)this.player.getSingle(arg0);
		CraftPlayer player = (CraftPlayer)p;
		if (p != null) {
			try {
				player.getHandle().getDataWatcher().watch(9, (byte)0);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
