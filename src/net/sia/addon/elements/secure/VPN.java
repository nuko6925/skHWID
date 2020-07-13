package net.sia.addon.elements.secure;

import org.bukkit.event.Event;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.sia.addon.Main;

public class VPN extends SimpleExpression<Boolean> {
	private Expression<Player> ex_p;

	@Override
	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		ex_p = (Expression<Player>)arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected Boolean[] get(Event arg0) {
		Player player = (Player)ex_p.getSingle(arg0);
		String ipAddress = player.getAddress().getAddress().toString();
		String ip = ipAddress.replace("/", "");
		if (ip.equals("127.0.0.1")) {
			try {
				String line = "localhost";
				URL url = new URL("https://api.ipify.org?format=raw");
				InputStreamReader in = new InputStreamReader(url.openStream());
				BufferedReader br = new BufferedReader(in);
				line = br.readLine();
				ip = line;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String body = "";
		HttpURLConnection conn = null;
		int status = -1;
		
		try {
			URL target = new URL("http://v2.api.iphub.info/ip/"+ip);
			conn = (HttpURLConnection)target.openConnection();
			conn.setRequestProperty("User-Agent", String.format("skHWID/%s (https://github.com/nuko6925/skHWID)", Main.getInstance().getDescription().getVersion()));
			conn.setRequestProperty("X-Key", "123456");
			conn.setUseCaches(false);
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			InputStream response = conn.getErrorStream();
			if (response == null) {
				response = conn.getInputStream();
			}
			String encoding = conn.getContentEncoding();
			if (encoding != null)
				if (encoding.equalsIgnoreCase("gzip")) {
					response = new GZIPInputStream(response);
				} else if (encoding.equalsIgnoreCase("deflate")) {
					response = new InflaterInputStream(response, new Inflater(true));
				}
			StringBuilder responseBody = new StringBuilder();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(response, StandardCharsets.UTF_8))) {
				String line;
				while ((line = br.readLine()) != null) {
					responseBody.append(line);
					responseBody.append("\n");
				}
			}
			status = conn.getResponseCode();
			body = responseBody.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		if (status == 200) {
//			Bukkit.getServer().getLogger().warning("skHWID>> "+body);
			String[] block = body.split(",");
			if (block[5].contains("block")) {
				if (block[5].contains("0") || block[5].contains("2")) {
					return new Boolean[] { false };
				} else if (block[5].contains("1")) {
					return new Boolean[] { true };
				} else {
					Bukkit.getServer().getLogger().warning("skHWID>> Unexpected error: There is something I do not know at the scheduled position.");
					return null;
				}
			} else {
				Bukkit.getServer().getLogger().warning("skHWID>> Unexpected error: There is no request at the expected position");
				return null;
			}
		} else if (status == 429) {
			Bukkit.getServer().getLogger().warning("skHWID>> API Limit!");
		}
		return null;
	}
}
