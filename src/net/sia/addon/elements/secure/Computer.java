package net.sia.addon.elements.secure;

import org.bukkit.event.Event;

import java.net.InetAddress;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class Computer extends SimpleExpression<String> {

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		return false;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		try {
			InetAddress computer = InetAddress.getLocalHost();
			return new String[] { computer.getHostName() };
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
