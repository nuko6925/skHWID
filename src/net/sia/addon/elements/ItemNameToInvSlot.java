package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class ItemNameToInvSlot extends Effect {
	Expression<Long> ex_l;
	Expression<Player> ex_p;
	Expression<String> ex_s;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.ex_l = (Expression<Long>)arg0[0];
		this.ex_p = (Expression<Player>)arg0[1];
		this.ex_s = (Expression<String>)arg0[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event arg0) {
		long l = (long)this.ex_l.getSingle(arg0);
		int i = (int)l;
		Player player = (Player)this.ex_p.getSingle(arg0);
		String string = (String)this.ex_s.getSingle(arg0);
		ItemStack itemStack = player.getInventory().getItem(i);
		if (itemStack != null) {
			ItemMeta meta = itemStack.getItemMeta();
			meta.setDisplayName(string);
			itemStack.setItemMeta(meta);
		}
			
	}

}
