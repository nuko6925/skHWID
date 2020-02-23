package net.sia.addon.elements;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class Actionbar extends Effect {
	private Expression<Player> player;
	private Expression<String> exp_bar;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.player = (Expression<Player>) arg0[0];
		this.exp_bar = (Expression<String>) arg0[1];
		return true;
	}
	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}
	@Override
	protected void execute(Event arg0) {
		String bar = (String)this.exp_bar.getSingle(arg0);
		IChatBaseComponent str = ChatSerializer.a("{\"text\":\"" + bar + "\"}");
		PacketPlayOutChat packet = new PacketPlayOutChat();
		try {
			Field field = packet.getClass().getDeclaredField("a");
			field.setAccessible(true);
			field.set(packet, str);
			field.setAccessible(!field.isAccessible());
			Field byteField = packet.getClass().getDeclaredField("b");
			byteField.setAccessible(true);
			byteField.set(packet, Byte.valueOf((byte)2));
			byteField.setAccessible(!byteField.isAccessible());
		} catch (Exception e) {
			e.printStackTrace();
		}
		((CraftPlayer)this.player.getSingle(arg0)).getHandle().playerConnection.sendPacket(packet);
		
	}

}
