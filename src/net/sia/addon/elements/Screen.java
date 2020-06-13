package net.sia.addon.elements;

import java.awt.Color;

import javax.swing.JFrame;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class Screen extends SimpleExpression<Boolean> {
	private Expression<Long> exp_x;
	private Expression<Long> exp_y;
	private Expression<String> exp_title;
	private Expression<Long> exp_r;
	private Expression<Long> exp_g;
	private Expression<Long> exp_b;
	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
		this.exp_x = (Expression<Long>) exprs[0];
		this.exp_y = (Expression<Long>) exprs[1];
		this.exp_title = (Expression<String>) exprs[2];
		this.exp_r = (Expression<Long>) exprs[3];
		this.exp_g = (Expression<Long>) exprs[4];
		this.exp_b = (Expression<Long>) exprs[5];
		return true;
	}

	public String toString(@Nullable Event event, boolean debug) {
		return "test";
	}
	

	public synchronized void sleep(long msec) {
		try {
			wait(msec);
		} catch (InterruptedException e) {}
	}
	
	public void createScreen(int width, int height, int red, int green, int blue, String name) {
		JFrame frame = new JFrame(name);
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(red, green, blue));
	}

	@Nullable
	protected Boolean[] get(Event event) {
		long x = (long)this.exp_x.getSingle(event);
		long y = (long)this.exp_y.getSingle(event);
		int x2 = (int)x;
		int y2 = (int)y;
		long r1 = (long)this.exp_r.getSingle(event);
		long g1 = (long)this.exp_g.getSingle(event);
		long b1 = (long)this.exp_b.getSingle(event);
		int r = (int)r1;
		int g = (int)g1;
		int b = (int)b1;
		String title = (String)this.exp_title.getSingle(event);
    	createScreen(x2, y2, r, g, b, title);
        try {
        	return new Boolean[] {true};
        } catch (Exception err) {
        	err.printStackTrace();
        }
		return null;
		}
	}