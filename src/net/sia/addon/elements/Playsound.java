package net.sia.addon.elements;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class Playsound extends Effect {
	private Expression<Player> player;
    private Expression<String> sound;
    private Expression<Number> vol;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		try {
            Sound.valueOf(arg0[0].toString().replace("\"", "").trim().replace(" ", "_").toUpperCase());
            this.sound = (Expression<String>) arg0[0];
        } catch (IllegalArgumentException var6) {
            Skript.error(arg0[0].toString().replace("\"", "") + " is not a valid sound type");
            return false;
        } catch (NullPointerException var7) {
            Skript.error(arg0[0].toString().replace("\"", "") + " is not a valid sound type");
            return false;
        }

        this.player = (Expression<Player>) arg0[1];
        this.vol = (Expression<Number>) arg0[2];
        return true;
	}
	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}
	@Override
	protected void execute(Event arg0) {
		Sound soundToPlay = Sound.valueOf(((String)this.sound.getSingle(arg0)).replace("\"", "").trim().replace(" ", "_").toUpperCase());
        Player[] var3;
        int var4;
        int var5;
        Player p;
        if (this.vol != null) {
            var3 = (Player[])this.player.getAll(arg0);
            var4 = var3.length;

            for(var5 = 0; var5 < var4; ++var5) {
                p = var3[var5];
                p.playSound(p.getLocation(), soundToPlay, (float)((Number)this.vol.getSingle(arg0)).doubleValue(), 1.0F);
            }
        }
		
	}

}
