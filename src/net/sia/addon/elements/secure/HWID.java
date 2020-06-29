package net.sia.addon.elements.secure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class HWID extends SimpleExpression<String> {
	String sha256 = "";
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	public boolean isSingle() {
		return true;
	}

	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
		return true;
	}

	public String toString(@Nullable Event event, boolean debug) {
		return "test";
	}
	public static String getSystem_SerialNumber(){
        try{
            String OSName=  System.getProperty("os.name");
            if(OSName.contains("Windows")){
                return (getWindows_SerialNumber());
            }
            else{
                return (GetLinux_serialNumber());
            }
        }
        catch(Exception E){
            System.err.println(E.getMessage());
            return null;
        }
    }

    private static String getWindows_SerialNumber() {
        String result = "";
        try {
            File file = File.createTempFile("check",".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs =
                    "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                            + "Set colItems = objWMIService.ExecQuery _ \n"
                            + "   (\"Select * from Win32_BaseBoard\") \n"
                            + "For Each objItem in colItems \n"
                            + "    Wscript.Echo objItem.SerialNumber \n"
                            + "    exit for  ' do the first cpu only! \n"
                            + "Next \n";

            fw.write(vbs);
            fw.close();

            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        }
        catch(Exception E){
            System.err.println(E.getMessage());
        }
        return result.trim();
    }


    private static String GetLinux_serialNumber() {
        String command = "dmidecode -s baseboard-serial-number";
        String sNum = null;
        try {
            Process SerNumProcess = Runtime.getRuntime().exec(command);
            BufferedReader sNumReader = new BufferedReader(new InputStreamReader(SerNumProcess.getInputStream()));
            sNum = sNumReader.readLine().trim();
            SerNumProcess.waitFor();
            sNumReader.close();
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
            sNum =null;
        }
        return sNum;
    }

	@Nullable
	protected String[] get(Event event) {
		String sha256 = "";
        String motherBoard_SerialNumber = getSystem_SerialNumber();
        System.out.println(motherBoard_SerialNumber);
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] result = digest.digest((motherBoard_SerialNumber).getBytes());
            sha256 = String.format("%040x", new BigInteger(1, result));
        	return new String[] {sha256};
        } catch (Exception err) {
        	err.printStackTrace();
        }
		return null;
		}
	}