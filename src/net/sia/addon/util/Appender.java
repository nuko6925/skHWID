package net.sia.addon.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import net.sia.addon.Main;

public class Appender extends AbstractAppender {
	public Appender() {
		super("skHWID", null, null, false);
		Logger rootLogger = (Logger) LogManager.getRootLogger();
		rootLogger.addAppender(this);
	}
	
	@Override
	public void append(LogEvent e) {
		if(!Main.getInstance().isEnabled())
			return;
		EvtLog eventLog = new EvtLog(e, e.getMessage());
		new BukkitRunnable() {
			public void run() {
				Bukkit.getServer().getPluginManager().callEvent(eventLog);
			}
		}.runTask(Main.getInstance());
	}

}
