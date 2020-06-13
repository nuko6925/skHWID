package net.sia.addon.elements;

import org.bukkit.event.Event;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.sia.addon.util.AbsolutePath;

public class CreateImage extends Effect {
	private Expression<String> ex_content;
	private Expression<String> ex_font;
	private Expression<Long> ex_font_size;
	private Expression<String> ex_name;
	private Expression<String> path;

	@Override
	protected void execute(Event e) {
		String content = (String)ex_content.getSingle(e);
		String font = (String)ex_font.getSingle(e);
		long l = (long)ex_font_size.getSingle(e);
		int font_size = (int)l;
		String name = (String)ex_name.getSingle(e);
		String str1 = content.replace(":new:", "‰E");
		int ypromote = 0;
		int max_width = 720;
		int max_height = 0;
		try {
			int initx = 20;
			int inity = 20;
			int news = 1;
			for (String str : str1.split("")) {
				if (str.equals("‰E")) {
					news++;
					initx = 20;
				} else {
					if (initx <= 700) {
						initx += font_size;
					} else {
						news++;
						initx = 20;
					}
				}
			}
			if (inity+(news*font_size) > 2160) {
				ypromote = inity+(news*font_size)-2160;
				max_width = (int) (720+(Math.ceil(ypromote/2)))/2+(font_size*4);
				max_height = (int) (inity+(news*font_size)-(Math.ceil(ypromote/2)))/2+(font_size*4);
				
			} else {
				max_width = 720;
				max_height = inity+(news*font_size);
			}
			BufferedImage image = new BufferedImage(max_width, max_height, BufferedImage.TYPE_3BYTE_BGR);
			Graphics g = image.getGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, max_width, max_height);
			g.setColor(Color.BLACK);
			g.setFont(new Font(font, Font.PLAIN, font_size));
			initx = 20;
			inity = 20;
			for (String str : str1.split("")) {
				g.setFont(new Font(font, Font.PLAIN, font_size));
				if (str.equals("‰E")) {
					inity += font_size;
					initx = 20;
				} else {
					if (initx <= (max_width-20)) {
						g.drawString(str, initx, inity);
						initx += font_size;
					} else {
						inity += font_size;
						initx = 20;
					}
				}
			}
			g.dispose();
			Path pth = Paths.get(AbsolutePath.getDefaultPath(path.getSingle(e))+"/"+font+"_"+name);
			Path fwn = Paths.get(pth.toString().replace(File.separator + pth.toString().substring(pth.toString().lastIndexOf(File.separator) + 1), ""));
			if (!Files.exists(fwn)) {
				Files.createDirectories(fwn);
			}
			ImageIO.write(image, "png", new File(pth.toString()));
		} catch (Exception x) {
			x.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
		ex_content = (Expression<String>) e[0];
		ex_font = (Expression<String>) e[1];
		ex_font_size = (Expression<Long>) e[2];
		ex_name = (Expression<String>) e[3];
		path = (Expression<String>) e[4];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean b) {
		return getClass().getName();
		}
	}