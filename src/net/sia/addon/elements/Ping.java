package net.sia.addon.elements;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class Ping extends Effect {
	private Expression<Player> player;
	private Expression<Long> ex_ping;
	private static String packageName = Bukkit.getServer().getClass().getPackage().getName();
	private static String version;
	private static Method handle;
    private static Field pingField;

    static {
        version = packageName.substring(packageName.lastIndexOf(".") + 1);
    }

    public Ping() {
    }
    
    @SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
        this.player = (Expression<Player>) expr[0];
        this.ex_ping = (Expression<Long>) expr[1];
        return true;
    }

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event arg0) {
		Player p = (Player)this.player.getSingle(arg0);
		long ms = (long)this.ex_ping.getSingle(arg0);
		int ping = (int)ms;
		if (p != null) {
			try {
				Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer");
	            Class<?> entityPlayer = Class.forName("net.minecraft.server." + version + ".EntityPlayer");
	            handle = craftPlayer.getMethod("getHandle");
	            pingField = entityPlayer.getField("ping");
	            Object converted = craftPlayer.cast(p);
	            Object entity = handle.invoke(converted);
	            pingField.setInt(entity, ping);
			} catch (Exception err) {
				err.printStackTrace();
			}
		}
	}
}