package net.sia.addon.elements.secure;

import org.bukkit.event.Event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

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
		
		HttpURLConnection urlConnection = null;
		InputStream iStream = null;
		BufferedReader bReader = null;
		String xml = "";
		
		try {
			URL url = new URL("http://v2.api.iphub.info/ip/"+ip);
			urlConnection = (HttpURLConnection)url.openConnection();
			urlConnection.setRequestProperty("X-Key: ", "Mzc2NjpOQlZpS0c1dUt1V3ljWnF3ZExrTHY0ZHp0a3FKTzJGVQ==");
			urlConnection.setRequestMethod("GET");
			urlConnection.connect();
			int status = urlConnection.getResponseCode();
			if (status == 200) {
				iStream = urlConnection.getInputStream();
				bReader = new BufferedReader(new InputStreamReader(iStream));
				StringBuilder output = new StringBuilder();
				String line;
				while ((line = bReader.readLine()) != null) {
					output.append(line);
					xml += line;
				}
			} else if (status == 429) {
				Bukkit.getServer().getLogger().warning("skHWID>> API Limit!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bReader != null) {
					bReader.close();
				}
				if (urlConnection != null) {
					urlConnection.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String[] json = xml.split(",");
		if (json[5].contains("block")) {
			if (json[5].contains("0") || json[5].contains("2")) {
				return new Boolean[] { false };
			} else if (json[5].contains("1")) {
				return new Boolean[] { true };
			}
		}
		return null;
	}

}
