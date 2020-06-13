package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.bukkit.util.Vector;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.sia.addon.Main;

public class Velocity extends Effect {
	private Expression<Player> ex_ent;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.ex_ent = (Expression<Player>) arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event arg0) {
		Player player = (Player)this.ex_ent.getSingle(arg0);
		final Vector vector = new Vector();
		player.setVelocity(vector);
		Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> player.setVelocity(vector), 1l);
	}
	

}
