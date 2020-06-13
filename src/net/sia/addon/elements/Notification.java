package net.sia.addon.elements;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class Notification extends SimpleExpression<Boolean> {
	private Expression<String> eff_hw;
	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
		this.eff_hw = (Expression<String>) exprs[0];
		return true;
	}

	public String toString(@Nullable Event event, boolean debug) {
		return "test";
	}

	@Nullable
	protected Boolean[] get(Event event) {
		String hw = (String)this.eff_hw.getSingle(event);
        try {
        	SystemTray tray = SystemTray.getSystemTray();
        	Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        	TrayIcon trayicon = new TrayIcon(image, "Skript");
        	trayicon.setImageAutoSize(true);
        	trayicon.setToolTip("Skript is God");
        	tray.add(trayicon);
        	trayicon.displayMessage("Skript", hw, MessageType.INFO);
        	return new Boolean[] {true};
        } catch (Exception err) {
        	err.printStackTrace();
        }
		return null;
		}
	}