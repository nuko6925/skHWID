package net.sia.addon.elements.math;

import org.bukkit.event.Event;

import java.math.BigDecimal;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class CalcPI extends SimpleExpression<String> {
	private Expression<Long> ex_font_size;

	private BigDecimal getPI(int yn) {
		double upperbase;
		double lowerbase;
		double height;
		double lowery;
		double area;
		double aread;
		
		height = 1.0 / (double)yn;
		upperbase = 0.0;
		lowery = 1.0 - height;
		area = 0.0;
		for (int i = 0; i < yn; ++i) {
			lowerbase = Math.sqrt(1.0 - lowery * lowery);
			aread = (upperbase + lowerbase) * height / 2.0;
			area += aread;
			upperbase = lowerbase;
			lowery -= height;
		}
		area = area * 4.0;
		return new BigDecimal(area);
	}

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
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
		ex_font_size = (Expression<Long>) e[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected String[] get(Event e) {
		try {
			long l = (long)ex_font_size.getSingle(e);
			int i = (int)l;
			BigDecimal decimal = getPI(i);
			return new String[] { decimal.toString() };
		} catch (Exception ee) {
			ee.printStackTrace();
			return null;
		}
	}
	

}
