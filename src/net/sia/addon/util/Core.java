package net.sia.addon.util;

import org.bukkit.entity.Player;

public abstract class Core
{
    public abstract void hidePlayer(final Player p0, final Player p1);
    
    public abstract void showPlayer(final Player p0, final Player p1);
    
    protected byte toByte(final float yaw_pitch) {
        return (byte)(yaw_pitch * 256.0f / 360.0f);
    }
}
