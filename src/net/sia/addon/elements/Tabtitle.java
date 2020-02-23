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
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;

public class Tabtitle extends Effect {
    private Expression<Player> player;
    private Expression<String> coreHeader;
    private Expression<String> coreFooter;

    @SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        this.player = (Expression<Player>) exp[2];
        this.coreHeader = (Expression<String>) exp[0];
        this.coreFooter = (Expression<String>) exp[1];
        return true;
    }

    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    protected void execute(Event evt) {
        IChatBaseComponent header = ChatSerializer.a("{\"text\": \"" + ((String)this.coreHeader.getSingle(evt)).replace("\"", "") + "\"}");
        IChatBaseComponent footer = ChatSerializer.a("{\"text\": \"" + ((String)this.coreFooter.getSingle(evt)).replace("\"", "") + "\"}");
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

        try {
            Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, header);
            headerField.setAccessible(!headerField.isAccessible());
            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, footer);
            footerField.setAccessible(!footerField.isAccessible());
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        ((CraftPlayer)this.player.getSingle(evt)).getHandle().playerConnection.sendPacket(packet);
    }
}