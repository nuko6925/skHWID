package net.sia.addon.elements.secure;

import org.bukkit.event.Event;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.sia.addon.util.AbsolutePath;

public class FileCRC extends SimpleExpression<String> {
	private Expression<String> ex_path;

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
		ex_path = (Expression<String>)arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@SuppressWarnings("resource")
	@Override
	@Nullable
	protected String[] get(Event arg0) {
		String path = (String)ex_path.getSingle(arg0);
		Path pth = Paths.get(AbsolutePath.getDefaultPath(path));
		File file = new File(pth.toString());
		try {
			CRC32 crc32 = new CRC32();
			BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
			int b;
			while ((b=buf.read())!=-1) {
				crc32.update(b);
			}
			return new String[] { Long.toHexString(crc32.getValue()) };
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
