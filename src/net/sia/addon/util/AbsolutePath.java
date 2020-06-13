package net.sia.addon.util;

import java.io.File;
import java.nio.file.Paths;

import org.bukkit.Bukkit;

public class AbsolutePath {
	public static String getDefaultPath(String pth) {
		if (!Bukkit.getPluginManager().getPlugin("skUtilities").getConfig().getBoolean("useRootAsDefaultPath", false)) {
			String dp = Paths.get("").normalize().toAbsolutePath().toString();
			if (pth.contains(dp)) {
				return (pth + File.separator);
			} else {
				return (dp + File.separator + pth);
			}
		} else {
			return pth;
		}
	}
}
