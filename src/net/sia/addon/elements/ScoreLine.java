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

public class ScoreLine extends Effect {
	Expression<Long> ex_long;
	Expression<Player> ex_p;
	Expression<String> ex_str;
	

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.ex_long = (Expression<Long>)arg0[0];
		this.ex_p = (Expression<Player>)arg0[1];
		this.ex_str = (Expression<String>)arg0[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event arg0) {
		long l = (long)this.ex_long.getSingle(arg0);
		int i = (int)l;
		CraftPlayer player = ((CraftPlayer)(Player)this.ex_p.getSingle(arg0));
		String string = (String)this.ex_str.getSingle(arg0);
		ScoreboardObjective objective;
		try {
			String name = player.getName();
			Scoreboard sb = new Scoreboard();
			ScoreboardObjective obj = sb.getObjective(name);
			if (obj == null) {
				objective = sb.registerObjective(name, IScoreboardCriteria.b);
			} else {
				objective = obj;
			}
	        ScoreboardScore score = new ScoreboardScore(sb, objective, string);
	        score.setScore(i);
	        PacketPlayOutScoreboardScore packet = new PacketPlayOutScoreboardScore(score);
	        player.getHandle().playerConnection.sendPacket(packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	

}
