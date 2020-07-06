package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class Collides extends Effect {
	private Expression<Player> ex_p;
	private Expression<Boolean> ex_b;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		ex_p = (Expression<Player>)arg0[0];
		ex_b = (Expression<Boolean>)arg0[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(final Event e) {
        final Player p = (Player)this.ex_p.getSingle(e);
        final Boolean b = (Boolean)this.ex_b.getSingle(e);
        p.spigot().setCollidesWithEntities((boolean)b);
    }

}
