package net.sia.addon.elements;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class Alert extends SimpleExpression<Boolean> {
	private Expression<String> exp_lore;
	String args = "";
	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
		this.exp_lore = (Expression<String>) exprs[0];
		return true;
	}

	public String toString(@Nullable Event event, boolean debug) {
		return "test";
	}
	
	public void createScreen(int width, int height, int red, int green, int blue, String name, String desc) {
		
		JFrame frame = new JFrame(name);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(red, green, blue));
		JTextField text = new JTextField(20);
		text.setText(desc);
		frame.add(text, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	

	@Nullable
	protected Boolean[] get(Event event) {
		
		String lore = (String)this.exp_lore.getSingle(event);
    	createScreen(265, 120, 255, 255, 255, "Message", lore);
        try {
        	return new Boolean[] {true};
        } catch (Exception err) {
        	err.printStackTrace();
        }
		return null;
		}
	}