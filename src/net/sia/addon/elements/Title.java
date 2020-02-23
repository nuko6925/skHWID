package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class Title extends Effect {
	private Expression<Player> player;
	private Expression<String> title;
	private Expression<String> subtitle;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.player = (Expression<Player>) arg0[0];
		this.title = (Expression<String>) arg0[1];
		this.subtitle = (Expression<String>) arg0[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event arg0) {
		PacketPlayOutTitle packet;
		PlayerConnection connect = ((CraftPlayer)this.player.getSingle(arg0)).getHandle().playerConnection;
		packet = new PacketPlayOutTitle(EnumTitleAction.TIMES, (IChatBaseComponent)null, 0, 60, 0);
		connect.sendPacket(packet);
		PacketPlayOutTitle pTitle;
		IChatBaseComponent fTitle;
		if (this.subtitle != null) {
            fTitle = ChatSerializer.a("{\"text\": \"" + ((String)this.subtitle.getSingle(arg0)).toString() + "\"}");
            pTitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, fTitle);
            connect.sendPacket(pTitle);
        }

        if (this.title != null) {
            fTitle = ChatSerializer.a("{\"text\": \"" + ((String)this.title.getSingle(arg0)).toString() + "\"}");
            pTitle = new PacketPlayOutTitle(EnumTitleAction.TITLE, fTitle);
            connect.sendPacket(pTitle);
        }
		
	}
	

}
