package net.sia.addon.util;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.message.Message;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EvtLog extends Event {
	private static final HandlerList handlers = new HandlerList();
	private LogEvent logEvent;

	public EvtLog(final LogEvent e, final Message msg) {
		logEvent = e;
	}

	public LogEvent getLogEvent() {
		return logEvent;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
