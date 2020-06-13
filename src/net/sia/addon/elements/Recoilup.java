package net.sia.addon.elements;

import org.bukkit.event.Event;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.minecraft.server.v1_8_R3.PacketPlayOutPosition;
import net.minecraft.server.v1_8_R3.PacketPlayOutPosition.EnumPlayerTeleportFlags;

public class Recoilup extends Effect {
	private static Set<EnumPlayerTeleportFlags> teleportFlags = new HashSet<>(Arrays.asList(EnumPlayerTeleportFlags.X, EnumPlayerTeleportFlags.Y, EnumPlayerTeleportFlags.Z));
	Expression<Player> ex_p;
	Expression<Number> ex_n;
	Expression<Number> ex_n2;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.ex_p = (Expression<Player>)arg0[0];
		this.ex_n = (Expression<Number>)arg0[1];
		this.ex_n2 = (Expression<Number>)arg0[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event arg0) {
		CraftPlayer player = (CraftPlayer)(Player)this.ex_p.getSingle(arg0);
		Player p = (Player)this.ex_p.getSingle(arg0);
		float pitch = (float)((Number)this.ex_n.getSingle(arg0)).doubleValue();
		float yaw = (float)((Number)this.ex_n2.getSingle(arg0)).doubleValue();
		PacketPlayOutPosition packet = new PacketPlayOutPosition(0.0, 0.0, 0.0, p.getLocation().getYaw() + yaw, p.getLocation().getPitch() + pitch, teleportFlags);
		player.getHandle().playerConnection.sendPacket(packet);
		
	}

}
