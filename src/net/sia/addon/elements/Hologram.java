package net.sia.addon.elements;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.WorldServer;

public class Hologram extends Effect {
	private Expression<Player> player;
	private Expression<String> text;

@SuppressWarnings("unchecked")
@Override
public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
	this.player = (Expression<Player>) arg0[0];
	this.text = (Expression<String>) arg0[1];
	return true;
}

@Override
public String toString(@Nullable Event arg0, boolean arg1) {
	return null;
}

@Override
protected void execute(Event arg0) {
	Player player = (Player)this.player.getSingle(arg0);
	CraftPlayer p = (CraftPlayer)player;
	String text = (String)this.text.getSingle(arg0);
	WorldServer worldServer = ((CraftWorld)p.getWorld()).getHandle();
	EntityArmorStand stand = new EntityArmorStand(worldServer);
	stand.setLocation(p.getLocation().getX(), p.getLocation().getY()+0.5, p.getLocation().getZ(), 0, 0);
	stand.setCustomName(text);
	stand.setCustomNameVisible(true);
	stand.setGravity(false);
	stand.setSmall(false);
	stand.setInvisible(true);
	try {
		PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(stand);
		p.getHandle().playerConnection.sendPacket(packet);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
};

}
