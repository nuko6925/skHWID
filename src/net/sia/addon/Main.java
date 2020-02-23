package net.sia.addon;

import java.math.BigInteger;

import org.bukkit.event.Listener;
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
		Skript.registerExpression(Version.class, String.class, ExpressionType.COMBINED, new String[] {"[skHWID] server version"});
		Skript.registerExpression(Version.class, String.class, ExpressionType.COMBINED, new String[] {"[skHWID] version of server"});
		Skript.registerExpression(JvmName.class, String.class, ExpressionType.COMBINED, new String[] {"[skHWID] jvm name"});
		Skript.registerExpression(JvmName.class, String.class, ExpressionType.COMBINED, new String[] {"[skHWID] name of jvm"});
		Skript.registerEffect(GC.class, new String[] {"[skHWID] run gc"});
		Skript.registerCondition(No.class, new String[] {"[skHWID] %object% != %object%"});
		Skript.registerCondition(Yes.class, new String[] {"[skHWID] %object% == %object%"});
		Skript.registerEffect(Arrow.class, new String[] {"[skHWID] delete stuck arrow of %player%"});
		Skript.registerEffect(Arrow.class, new String[] {"[skHWID] delete %player%'s stuck arrow"});
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
		Skript.registerExpression(CubeRoot.class, Double.class, ExpressionType.COMBINED, new String[] {"[skHWID] cbrt of %number%"});
		Skript.registerExpression(Fact.class, BigInteger.class, ExpressionType.COMBINED, new String[] {"[skHWID] fact of %long%"});
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