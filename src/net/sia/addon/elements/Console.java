package net.sia.addon.elements;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class Console extends SimpleExpression<String> {
	String getlog = "";
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	public boolean isSingle() {
		return true;
	}

	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
		return true;
	}

	public String toString(@Nullable Event event, boolean debug) {
		return "test";
	}

	public void onEnable() {
		Bukkit.getServer().getLogger().addHandler(new Handler() {
			public void close() throws SecurityException {}
			
			public void flush() {}
			
			public void publish(LogRecord logRecord) {
				String lv = logRecord.getLevel().toString();
				String mes = logRecord.getMessage();
				getlog = (lv + mes);
			}
		});
	}
	@Nullable
	protected String[] get(Event event) {
        try {
        	return new String[] {getlog};
        } catch (Exception err) {
        	err.printStackTrace();
        }
		return null;
	}

	public void onDisable() {
		
	}
}