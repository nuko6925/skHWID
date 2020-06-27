package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.bukkit.util.Vector;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class CanSee extends SimpleExpression<Boolean> {
	private Expression<Player> ex_p1;
	private Expression<Player> ex_p2;
	
	private boolean checkBlock(final Location location1, final Location location2) {
		boolean mainResult = false;
		for (int acc = 12, c = 365; c > 0; c -= 365/acc) {
			final double i = 0.3 * Math.cos(Math.toRadians(c));
			final double n = 0.3 * Math.sin(Math.toRadians(c));
			boolean result = true;
			final Location location3 = new Location(location2.getWorld(), location2.getX() + i, location2.getY(), location2.getZ() + n);
            final Vector pos1 = location1.toVector();
            final Vector pos2 = location3.toVector();
            final Vector vector = pos2.clone().subtract(pos1).normalize().multiply(0.1);
            final double distance = location1.distance(location3);
            if (distance <= 50.0) {
                double covered = 0.0;
                while (covered < distance) {
                    covered += 0.1;
                    final Block block = new Location(location1.getWorld(), pos1.getX(), pos1.getY(), pos1.getZ()).getBlock();
                    if (!block.isLiquid() && !block.getType().isTransparent()) {
                        if (block.getType().isOccluding()) {
                            if (block.getType() != Material.AIR) {
                                result = false;
                                break;
                            }
                        }
                    }
                    pos1.add(vector);
                }
                mainResult = (mainResult || result);
            }
        }
        return mainResult;
	}

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
		ex_p1 = (Expression<Player>)arg0[0];
		ex_p2 = (Expression<Player>)arg0[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected Boolean[] get(Event arg0) {
		Player player1 = (Player)ex_p1.getSingle(arg0);
		Player player2 = (Player)ex_p2.getSingle(arg0);
		final Location loc1 = player1.getEyeLocation();
		final Location loc2 = player2.getLocation();
		boolean result;
		double height;
		double d;
		if (player1.getWorld() != player2.getWorld()) {
			return new Boolean[] { false };
		} else if (player1.getGameMode() == GameMode.SPECTATOR) {
			return new Boolean[] { true };
		} else if (player1.getWorld() == player2.getWorld()) {
			result = this.checkBlock(loc1, loc2);
            for (height = 1.8, d = 0.1; d <= height; d += 0.1) {
                loc2.add(0.0, 0.1, 0.0);
                result = (result || this.checkBlock(loc1, loc2));
            }
            return new Boolean[] { result };
		}
		return null;
	}

}
