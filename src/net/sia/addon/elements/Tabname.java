package net.sia.addon.elements;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class Tabname extends Effect {
	private Expression<Player> player;
	private Expression<String> name;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.player = (Expression<Player>) arg0[0];
		this.name = (Expression<String>) arg0[1];
		return true;
	}
	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}
	@Override
	protected void execute(Event arg0) {
		String nameString = (String)this.name.getSingle(arg0);
		((CraftPlayer)this.player.getSingle(arg0)).setPlayerListName(nameString);
		
	}

}
