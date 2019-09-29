package dev.odod.actionbarhider;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.odod.actionbarhider.commands.CommandActionBarHider;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

@Mod(modid = "actionbarhider", version = "v1.1.0")
public class ActionBarHider {

    public static boolean toggled = true;
    public static boolean messageEnabled = true;
    public static String message = "\u00a7c[\u00a7fActionBarHider\u00a7c] \u00a7aActive - \u00a7c/abh for commands";

    private File cfgFile = null;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        cfgFile = event.getSuggestedConfigurationFile();
        loadCfg();
        Runtime.getRuntime().addShutdownHook(new Thread(this::saveCfg));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new CommandActionBarHider());
    }

    @SubscribeEvent
    public void onChatReceive(ClientChatReceivedEvent e) {
        if (toggled) {
            if (e.type == 2) {
                if (messageEnabled) {
                    e.message = new ChatComponentText(message);
                } else {
                    e.setCanceled(true);
                }
            }
        }
    }

    private void loadCfg() {
        try {
            JsonObject config = new JsonParser().parse(FileUtils.readFileToString(cfgFile)).getAsJsonObject();

            if (config.has("toggled")) toggled = config.get("toggled").getAsBoolean();
            if (config.has("messageEnabled")) messageEnabled = config.get("messageEnabled").getAsBoolean();
            if (config.has("message")) message = config.get("message").getAsString();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCfg() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("toggled", toggled);
            jsonObject.addProperty("messageEnabled", messageEnabled);
            jsonObject.addProperty("message", message);

            FileUtils.writeStringToFile(cfgFile, jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
