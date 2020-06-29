package net.sia.addon.elements.secure;

import org.bukkit.event.Event;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.sia.addon.util.AbsolutePath;

public class GetFilesizem extends SimpleExpression<Double> {
	private Expression<String> ex_path;

	@Override
	public Class<? extends Double> getReturnType() {
		return Double.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		ex_path = (Expression<String>)arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected Double[] get(Event arg0) {
		String path = (String)ex_path.getSingle(arg0);
		Path pth = Paths.get(AbsolutePath.getDefaultPath(path));
		File file = new File(pth.toString());
		Double size = file.length() / 1024.0 / 1024.0;
		return new Double[] { size };
	}

}
