package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class Value extends Effect {
	private Expression<String> ex_str;
	private Expression<Object> ex_obj;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.ex_str = (Expression<String>) arg0[0];
		this.ex_obj = (Expression<Object>) arg0[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event arg0) {
		String name = (String)this.ex_str.getSingle(arg0);
		Object obj = (Object)this.ex_obj.getSingle(arg0);
		String command = "setvariable " + name + " " + obj;
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
		
	}
	

}
