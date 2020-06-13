package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.sia.addon.Main;

public class VelocityHorizontal extends Effect {
	private Expression<Player> ex_ent;
	private Expression<Number> ex_hor;
	private Expression<Number> ex_ver;
	private Expression<Player> ex_dmg;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.ex_ent = (Expression<Player>) arg0[0];
		this.ex_hor = (Expression<Number>) arg0[1];
		this.ex_ver = (Expression<Number>) arg0[2];
		this.ex_dmg = (Expression<Player>) arg0[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event arg0) {
		Player player = (Player)this.ex_ent.getSingle(arg0);
		Number hor = (Number)this.ex_hor.getSingle(arg0);
		Number ver = (Number)this.ex_ver.getSingle(arg0);
		Player attacker = (Player)this.ex_dmg.getSingle(arg0);
		double h = hor.doubleValue();
		double v = ver.doubleValue();
		player.setVelocity(attacker.getLocation().getDirection().setY(player.getVelocity().getY() * v).normalize().multiply(player.getVelocity().getX() * h));
		Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> player.setVelocity(attacker.getLocation().getDirection().setY(player.getVelocity().getY() * v).normalize().multiply(player.getVelocity().getX() * h)), 1l);
	}
	

}
