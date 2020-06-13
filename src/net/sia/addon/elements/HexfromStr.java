package net.sia.addon.elements;

import org.bukkit.event.Event;

import javax.xml.bind.DatatypeConverter;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class HexfromStr extends SimpleExpression<String> {
	private Expression<String> ex_str;

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
		ex_str = (Expression<String>) arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		String str = (String)ex_str.getSingle(arg0);
		try {
			byte[] data = str.getBytes();
			String hexString = DatatypeConverter.printHexBinary(data);
			return new String[] { hexString };
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
