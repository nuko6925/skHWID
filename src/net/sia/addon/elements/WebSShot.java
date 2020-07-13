package net.sia.addon.elements;

import org.bukkit.event.Event;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.sia.addon.util.AbsolutePath;

public class WebSShot extends Effect {
	private Expression<String> ex_s;
	private Expression<String> ex_path;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		ex_s = (Expression<String>)arg0[0];
		ex_path = (Expression<String>)arg0[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event arg0) {
		String url = (String)ex_s.getSingle(arg0);
		System.setProperty("webdriver.chrome.driver","C:\\Users\\YuzuN\\Desktop\\Java\\Library\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		File scFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Path pth = Paths.get(AbsolutePath.getDefaultPath(ex_path.getSingle(arg0)));
		try {
			Thread.sleep(15000);
			BufferedImage image = ImageIO.read(scFile);
			ImageIO.write(image, "png", new File(pth.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Bukkit.getServer().getLogger().info("skHWID>> " + scFile.getAbsolutePath());
	}

}
