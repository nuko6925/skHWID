package net.sia.addon.elements.secure;

import org.bukkit.event.Event;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class WlPlayer extends SimpleExpression<Boolean> {
	private Expression<OfflinePlayer> ex_p;

	@Override
	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		ex_p = (Expression<OfflinePlayer>)arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected Boolean[] get(Event arg0) {
		OfflinePlayer player = (OfflinePlayer)ex_p.getSingle(arg0);
		return new Boolean[] { Bukkit.getServer().getWhitelistedPlayers().contains(player) };
	}

}
