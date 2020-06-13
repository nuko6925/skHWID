package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.minecraft.server.v1_8_R3.*;

public class ScoreRemove extends Effect {
	Expression<Player> ex_player;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.ex_player = (Expression<Player>) arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event arg0) {
		CraftPlayer player = ((CraftPlayer)(Player)this.ex_player.getSingle(arg0));
		try {
			String name = player.getName();
			Scoreboard sb = new Scoreboard();
	        ScoreboardObjective obj = sb.getObjective(name);
	        sb.unregisterObjective(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
