package net.sia.addon.elements;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class ShowArmor extends Effect {
	private Expression<String> ex_str;
	private Expression<Player> ex_player;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		this.ex_str = (Expression<String>) arg0[0];
		this.ex_player = (Expression<Player>) arg0[1];
		return true;
	}
	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}
	@Override
	protected void execute(Event arg0) {
		String str = (String)this.ex_str.getSingle(arg0);
		Player player = (Player)this.ex_player.getSingle(arg0);
		if (str.equals("helmet")) {
			PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(player.getEntityId(), 39, CraftItemStack.asNMSCopy(player.getEquipment().getHelmet()));
			for (Player otherPlayer : player.getWorld().getPlayers()) {
				PlayerConnection playerConnection = ((CraftPlayer)otherPlayer).getHandle().playerConnection;
				playerConnection.sendPacket(packet);
			}
		} else if (str.equals("chestplate")) {
			PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(player.getEntityId(), 38, CraftItemStack.asNMSCopy(player.getEquipment().getChestplate()));
			for (Player otherPlayer : player.getWorld().getPlayers()) {
				PlayerConnection playerConnection = ((CraftPlayer)otherPlayer).getHandle().playerConnection;
				playerConnection.sendPacket(packet);
			}
		} else if (str.equals("leggings")) {
			PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(player.getEntityId(), 37, CraftItemStack.asNMSCopy(player.getEquipment().getLeggings()));
			for (Player otherPlayer : player.getWorld().getPlayers()) {
				PlayerConnection playerConnection = ((CraftPlayer)otherPlayer).getHandle().playerConnection;
				playerConnection.sendPacket(packet);
			}
		} else if (str.equals("boots")) {
			PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(player.getEntityId(), 36, CraftItemStack.asNMSCopy(player.getEquipment().getBoots()));
			for (Player otherPlayer : player.getWorld().getPlayers()) {
				PlayerConnection playerConnection = ((CraftPlayer)otherPlayer).getHandle().playerConnection;
				playerConnection.sendPacket(packet);
			}
		} else if (str.equals("all")) {
			PacketPlayOutEntityEquipment packet1 = new PacketPlayOutEntityEquipment(player.getEntityId(), 39, CraftItemStack.asNMSCopy(player.getEquipment().getHelmet()));
			PacketPlayOutEntityEquipment packet2 = new PacketPlayOutEntityEquipment(player.getEntityId(), 38, CraftItemStack.asNMSCopy(player.getEquipment().getChestplate()));
			PacketPlayOutEntityEquipment packet3 = new PacketPlayOutEntityEquipment(player.getEntityId(), 37, CraftItemStack.asNMSCopy(player.getEquipment().getLeggings()));
			PacketPlayOutEntityEquipment packet4 = new PacketPlayOutEntityEquipment(player.getEntityId(), 36, CraftItemStack.asNMSCopy(player.getEquipment().getBoots()));
			for (Player otherPlayer : player.getWorld().getPlayers()) {
				PlayerConnection playerConnection = ((CraftPlayer)otherPlayer).getHandle().playerConnection;
				playerConnection.sendPacket(packet1);
				playerConnection.sendPacket(packet2);
				playerConnection.sendPacket(packet3);
				playerConnection.sendPacket(packet4);
			}
		} else {
			Bukkit.getLogger().severe("skHWID Trouble report: " + str + " is not armor value.");
		}
		
	};

}
