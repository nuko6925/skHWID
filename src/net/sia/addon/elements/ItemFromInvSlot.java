package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ItemFromInvSlot extends SimpleExpression<ItemStack>{
	Expression<Long> ex_l;
	Expression<Player> ex_p;

	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.ex_l = (Expression<Long>)arg0[0];
		this.ex_p = (Expression<Player>)arg0[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected ItemStack[] get(Event arg0) {
		long l = (long)this.ex_l.getSingle(arg0);
		int i = (int)l;
		Player player = (Player)this.ex_p.getSingle(arg0);
		ItemStack itemStack = player.getInventory().getItem(i);
		if (itemStack != null) {
			return new ItemStack[] { itemStack };
		} else {
			return null;
		}
	}

}
