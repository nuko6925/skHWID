package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;

import net.sia.addon.util.EvtLog;

public class LogEvt extends SkriptEvent {
	public String get(EvtLog e) {
				return e.getLogEvent().getMessage().getFormattedMessage();
			}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	public boolean check(Event arg0) {
		return arg0 instanceof EvtLog;
	}

	@Override
	public boolean init(Literal<?>[] arg0, int arg1, ParseResult arg2) {
		return true;
	}
	

}
