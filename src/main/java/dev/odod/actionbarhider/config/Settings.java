package dev.odod.actionbarhider.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Settings {

    private File cfgFile = new File(Minecraft.getMinecraft().mcDataDir, "config/ActionBarHider.json");

    public static boolean toggled = true;
    public static boolean messageEnabled = true;
    private static String message = "\u00a7c[\u00a7fActionBarHider\u00a7c] \u00a7aActive - \u00a7c/abh for commands";

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        Settings.message = message;
    }

    public void loadCfg() {
        try {
            JsonObject config = new JsonParser().parse(FileUtils.readFileToString(cfgFile)).getAsJsonObject();

            if (config.has("toggled")) toggled = config.get("toggled").getAsBoolean();
            if (config.has("messageEnabled")) messageEnabled = config.get("messageEnabled").getAsBoolean();
            if (config.has("message")) setMessage(config.get("message").getAsString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCfg() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("toggled", toggled);
            jsonObject.addProperty("messageEnabled", messageEnabled);
            jsonObject.addProperty("message", getMessage());

            FileUtils.writeStringToFile(cfgFile, jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
