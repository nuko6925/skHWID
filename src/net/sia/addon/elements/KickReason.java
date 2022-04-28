package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;

public class KickReason extends SimpleExpression<String>{

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		if (!ScriptLoader.isCurrentEvent((Class)PlayerKickEvent.class)) {
			Skript.error("omae baka", ErrorQuality.SEMANTIC_ERROR);
			return false;
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "aho";
	}

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		final PlayerKickEvent event = (PlayerKickEvent)arg0;
		return new String[] { event.getReason() };
	}
	
	

}
