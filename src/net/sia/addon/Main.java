package net.sia.addon;

import java.math.BigInteger;

import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.lang.ExpressionType;
import net.sia.addon.elements.*;
import net.sia.addon.util.*;

public class Main extends JavaPlugin implements Listener {
	private static Main instance;
	private static SkriptAddon addonInstance;
	public Main() {
		if (instance == null) {
			instance = this;
		} else {
			throw new IllegalStateException();
		}
	}
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		Skript.registerAddon(this);
		Skript.registerExpression(HWID.class, String.class, ExpressionType.COMBINED, new String[] {"[skHWID] hwid"});
		Skript.registerExpression(Uptime.class, String.class, ExpressionType.COMBINED, new String[] {"[skHWID] uptime"});
		Skript.registerExpression(User.class, String.class, ExpressionType.COMBINED, new String[] {"[skHWID] user"});
		Skript.registerExpression(Stepping.class, String.class, ExpressionType.COMBINED, new String[] {"[skHWID] stepping"});
		Skript.registerExpression(BorderSize.class, String.class, ExpressionType.COMBINED, new String[] {"[skHWID] border size of %string%"});
		Skript.registerExpression(GetDate.class, String.class, ExpressionType.COMBINED, new String[] {"[skHWID] get date from %long%"});
		Skript.registerExpression(Nanotime.class, Long.class, ExpressionType.COMBINED, new String[] {"[skHWID] [system] nanotime"});
		Skript.registerExpression(Notification.class, Boolean.class, ExpressionType.COMBINED, new String[] {"[skHWID] system notify to %string%"});
		Skript.registerExpression(Motherboard.class, String.class, ExpressionType.COMBINED, new String[] {"[skHWID] [system] board"});
		Skript.registerExpression(Cpuload1.class, Double.class, ExpressionType.COMBINED, new String[] {"[skHWID] [jvm] cpuload"});
		Skript.registerExpression(Cpuload2.class, Double.class, ExpressionType.COMBINED, new String[] {"[skHWID] [system] cpuload"});
		Skript.registerExpression(Screen.class, Boolean.class, ExpressionType.COMBINED, new String[] {"[skHWID] (create|make) (screen|window) size of %long%, %long% with title %string% with color %long%, %long%, %long%"});
		Skript.registerExpression(Alert.class, Boolean.class, ExpressionType.COMBINED, new String[] {"[skHWID] (create|make) alert of %string%"});
		Skript.registerEffect(Ping.class, new String[] {"[skHWID] set ping of %player% to %long%"});
		Skript.registerEffect(Ping.class, new String[] {"[skHWID] set %player%'s ping to %long%"});
		Skript.registerExpression(CPing.class, Integer.class, ExpressionType.COMBINED, new String[] {"[skHWID] %player%'s ping", "[skHWID] ping of %player%"});
		Skript.registerExpression(Version.class, String.class, ExpressionType.COMBINED, new String[] {"[skHWID] server version", "[skHWID] version of server"});
		Skript.registerExpression(JvmName.class, String.class, ExpressionType.COMBINED, new String[] {"[skHWID] jvm name", "[skHWID] name of jvm"});
		Skript.registerEffect(GC.class, new String[] {"[skHWID] run gc"});
		Skript.registerCondition(No.class, new String[] {"[skHWID] %object% != %object%"});
		Skript.registerCondition(Yes.class, new String[] {"[skHWID] %object% == %object%"});
		Skript.registerEffect(Arrow.class, new String[] {"[skHWID] delete stuck arrow of %player%", "[skHWID] delete %player%'s stuck arrow"});
		Skript.registerEffect(Hologram.class, new String[] {"[skHWID] set comment of %player% to %string%"});
		Skript.registerEffect(Hologram.class, new String[] {"[skHWID] set %player%'s comment to %string%"});
		Skript.registerEffect(Tabtitle.class, new String[] {"[skHWID] set tab header to %string% and footer to %string% for %player%"});
		Skript.registerEffect(Tabname.class, new String[] {"[skHWID] set %player% tab name to %string%"});
		Skript.registerEffect(Actionbar.class, new String[] {"[skHWID] set action bar of %player% to %string%"});
		Skript.registerEffect(Title.class, new String[] {"[skHWID] send %player% title %string% with subtitle %string%"});
		Skript.registerEffect(Playsound.class, new String[] {"[skHWID] play %string% to %player% at volume %number%"});
		Skript.registerExpression(Isflying.class, Boolean.class, ExpressionType.COMBINED, new String[] {"[skHWID] %player% isflying"});
		Skript.registerEffect(Flight.class, new String[] {"[skHWID] %player% setflight %boolean%"});
		Skript.registerExpression(Flight2.class, Boolean.class, ExpressionType.COMBINED, new String[] {"[skHWID] %player% flight"});
		Skript.registerEffect(Dropfix.class, new String[] {"[skHWID] drop fix %itemtype% at %location%"});
		Skript.registerEffect(Value.class, new String[] {"[skHWID] %string% to %object%"});
		Skript.registerExpression(UUID.class, String.class, ExpressionType.COMBINED, new String[] {"[skHWID] %player% id"});
		Skript.registerExpression(Plugin.class, Boolean.class, ExpressionType.COMBINED, new String[] {"[skHWID] %string% is enabled"});
		Skript.registerExpression(GetDateNano.class, Long.class, ExpressionType.COMBINED, new String[] {"[skHWID] seconds from nano %long%"});
		Skript.registerExpression(Upper.class, String.class, ExpressionType.COMBINED, new String[] {"[skHWID] upper string from %string%"});
		Skript.registerExpression(Lower.class, String.class, ExpressionType.COMBINED, new String[] {"[skHWID] lower string from %string%"});
		Skript.registerEffect(HideArmor.class, new String[] {"[skHWID] hide armor %string% of %player%"});
		Skript.registerEffect(ShowArmor.class, new String[] {"[skHWID] show armor %string% of %player%"});
		Skript.registerExpression(GetHotbarSlot.class, Long.class, ExpressionType.COMBINED, "[skHWID] get hotbar slot of %player%");
		Skript.registerEffect(ItemNameToInvSlot.class, "[skHWID] set item name from slot %long% of %player%['s inventory] to %string%");
		Skript.registerExpression(ItemNameFromInvSlot.class, String.class, ExpressionType.COMBINED, "[skHWID] get item name from slot %long% of %player%['s inventory]");
		Skript.registerExpression(ItemFromInvSlot.class, ItemStack.class, ExpressionType.COMBINED, "[skHWID] get item from slot %long% of %player%['s inventory]");
		Skript.registerExpression(CubeRoot.class, Double.class, ExpressionType.COMBINED, new String[] {"[skHWID] cbrt of %number%"});
		Skript.registerExpression(Fact.class, BigInteger.class, ExpressionType.COMBINED, new String[] {"[skHWID] fact of %long%"});
		Skript.registerEffect(Velocity.class, new String[] {"[skHWID] (delete|cancel) %player%'s (knockback|kb|velocity)"});
		Skript.registerEffect(VelocityHorizontal.class, new String[] {"[skHWID] make (knockback|velocity) %player% horizon %number% vertical %number% by %player%"});
		Skript.registerEffect(VelocityForce.class, new String[] {"[skHWID] set (knockback|velocity) %player% horizon %number% vertical %number% by %player%"});
		Skript.registerExpression(InventoryClickType.class, ClickType.class, ExpressionType.COMBINED, "[skHWID] (get|inventory) clicked type");
		Skript.registerEffect(Recoilup.class, "[skHWID] make %player% recoil up[ward] at %-number% and side at %-number%");
		Skript.registerEffect(CreateImage.class, "[skHWID] (create|make) image contents %string% with font %string% with size %long% named %string% at %string%");
		Skript.registerExpression(HexfromStr.class, String.class, ExpressionType.COMBINED, "[skHWID] hex from %string%");
		Skript.registerExpression(StrfromHex.class, String.class, ExpressionType.COMBINED, "[skHWID] str[ing] from %string%");
		Skript.registerExpression(HexfromBin.class, String.class, ExpressionType.COMBINED, "[skHWID] hex as bin[ary] %string%");
		Skript.registerEvent("Log", LogEvt.class, EvtLog.class, new String[] {"[skHWID] [server] log"});
		Skript.registerExpression(LogExp.class, String.class, ExpressionType.COMBINED, new String[] {"[skHWID] [server] event-log"});
		Skript.registerExpression(LogLevel.class, String.class, ExpressionType.COMBINED, new String[] {"[skHWID] [server] event-level"});
		Skript.registerExpression(LogSender.class, String.class, ExpressionType.COMBINED, new String[] {"[skHWID] [server] event-sender"});
		new Appender().start();
	}
	
	public static Main getInstance() {
		if (instance == null) {
			throw new IllegalStateException();
		} else {
			return instance;
		}
	}

	public static SkriptAddon getAddonInstance() {
		if (addonInstance == null) {
			addonInstance = Skript.registerAddon(getInstance());
		}
		return addonInstance;
	}

	
}