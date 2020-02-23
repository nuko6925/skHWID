package net.sia.addon.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

import net.minecraft.server.v1_8_R3.DamageSource;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityMinecartRideable;

public class PayloadMinecart extends EntityMinecartRideable {
	public PayloadMinecart(World world) {
		super(((CraftWorld)world).getHandle());
	}
	
	public void spawnAt(Location location) {
		setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
		((CraftWorld)location.getWorld()).getHandle().addEntity(this);
	}
	
	@Override
	public boolean damageEntity(DamageSource damageSource, float f) {
		return false;
	}
	
	@Override
	public void collide(Entity entity) {
	}
	

}
