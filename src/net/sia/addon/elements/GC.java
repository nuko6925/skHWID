package net.sia.addon.elements;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class GC extends Effect {

	    public GC() {
	    }
	    
		public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
	        return true;
	    }

		@Override
		public String toString(@Nullable Event arg0, boolean arg1) {
			return null;
		}

		@Override
		protected void execute(Event arg0) {
			MemoryMXBean mMx = ManagementFactory.getMemoryMXBean();
			try {
				mMx.gc();
			} catch (Exception err) {
				err.printStackTrace();
			}
		}
	}