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

public class ScoreTitle extends Effect {
	Expression<Player> ex_player;
	Expression<String> ex_str;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.ex_player = (Expression<Player>) arg0[0];
		this.ex_str = (Expression<String>) arg0[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event arg0) {
		Player player = (Player)this.ex_player.getSingle(arg0);
		String string = (String)this.ex_str.getSingle(arg0);
		CraftPlayer cPlayer = (CraftPlayer)player;
		ScoreboardObjective objective;
		try {
			String name = cPlayer.getName();
			Scoreboard scoreboard = new Scoreboard();
			scoreboard.unregisterObjective(scoreboard.getObjective(name));
			ScoreboardObjective obj = scoreboard.getObjective(name);
			if (obj == null) {
				objective = scoreboard.registerObjective(name, IScoreboardCriteria.b);
			} else {
				objective = obj;
			}
			objective.setDisplayName(string);
			PacketPlayOutScoreboardObjective createPacket = new PacketPlayOutScoreboardObjective(objective, 0);
			PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, objective);
			cPlayer.getHandle().playerConnection.sendPacket(createPacket);
			cPlayer.getHandle().playerConnection.sendPacket(display);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
