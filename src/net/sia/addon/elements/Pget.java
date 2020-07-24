package net.sia.addon.elements;

import org.bukkit.event.Event;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.sia.addon.Main;
import net.sia.addon.util.AbsolutePath;

public class Pget extends Effect {
	Expression<String> ex_path;
	Expression<Long> ex_x;
	Expression<Long> ex_y;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		ex_path = (Expression<String>)arg0[0];
		ex_x = (Expression<Long>)arg0[1];
		ex_y = (Expression<Long>)arg0[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event arg0) {
		String path = (String)ex_path.getSingle(arg0);
		long xx = (long)ex_x.getSingle(arg0);
		long yy = (long)ex_y.getSingle(arg0);
		int x = Math.toIntExact(xx);
		int y = Math.toIntExact(yy);
		Path pth = Paths.get(AbsolutePath.getDefaultPath(path));
		File file = new File(pth.toString());
		BufferedImage image = null;;
		try {
			image = ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (image != null) {
			Main.ginfo.set(0, image.getWidth());
			Main.ginfo.set(1, image.getHeight());
			Color color = new Color(image.getRGB(x, y));
			Main.ginfo.set(2, color.getRed());
			Main.ginfo.set(3, color.getGreen());
			Main.ginfo.set(4, color.getBlue());
			Main.ginfo.set(5, color.getAlpha());
		}
		
	}

}
