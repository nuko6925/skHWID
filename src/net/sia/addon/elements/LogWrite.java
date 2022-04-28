package net.sia.addon.elements;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.sia.addon.Main;
import net.sia.addon.util.AbsolutePath;

public class LogWrite extends Effect {
	Expression<String> ex_str;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		ex_str = (Expression<String>)arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event arg0) {
		String s1 = (String)ex_str.getSingle(arg0);
		if (Main.logPath.isEmpty()) {
			Skript.error("om a eah o, in it sir o");
		} else {
			Path pth = Paths.get(AbsolutePath.getDefaultPath(Main.logPath));
			File file = new File(pth.toString());
			if (file.exists()) {
				try {
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write(s1);
					fileWriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	}

}
