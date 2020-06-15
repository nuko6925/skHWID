package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.bukkit.Location;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class Particle extends Effect {
	private Expression<Long> ex_r;
	private Expression<Long> ex_g;
	private Expression<Long> ex_b;
	private Expression<Location> ex_l;
	private Expression<Number> ex_speed;
	private Expression<Long> ex_count;
	private Expression<Player> ex_player;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		ex_r = (Expression<Long>)arg0[0];
		ex_g = (Expression<Long>)arg0[1];
		ex_b = (Expression<Long>)arg0[2];
		ex_l = (Expression<Location>)arg0[3];
		ex_speed = (Expression<Number>)arg0[4];
		ex_count = (Expression<Long>)arg0[5];
		ex_player = (Expression<Player>)arg0[6];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event arg0) {
		long r = (long)ex_r.getSingle(arg0);
		long g = (long)ex_g.getSingle(arg0);
		long b = (long)ex_b.getSingle(arg0);
		Location l = (Location)ex_l.getSingle(arg0);
		Number speed = (Number)ex_speed.getSingle(arg0);
		long count = (long)ex_count.getSingle(arg0);
		Player player = (Player)ex_player.getSingle(arg0);
		CraftPlayer cPlayer = (CraftPlayer)player;
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, (float)l.getX(), (float)l.getY(), (float)l.getZ(), r/255, g/255, b/255, speed.floatValue(), (int)count);
		cPlayer.getHandle().playerConnection.sendPacket(packet);
	}

}
