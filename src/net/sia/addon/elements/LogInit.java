package net.sia.addon.elements;

import org.bukkit.event.Event;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.sia.addon.Main;
import net.sia.addon.util.AbsolutePath;

public class LogInit extends Effect {
	Expression<String> ex_path;

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
	protected void execute(Event arg0) {
		String path = (String)ex_path.getSingle(arg0);
		Path pth = Paths.get(AbsolutePath.getDefaultPath(path));
		File file = new File(pth.toString());
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Main.logPath = pth.toString();
	}

}
