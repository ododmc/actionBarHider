package dev.odod.actionbarhider;

import dev.odod.actionbarhider.commands.CommandActionBarHider;
import dev.odod.actionbarhider.config.Settings;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid = "actionbarhider", version = "v1.2.0")
public class ActionBarHider {

    private Settings settings = new Settings();

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new CommandActionBarHider(this));
        ClientRegistry.registerKeyBinding(settings.hotkey);
        settings.loadCfg();
    }

    @SubscribeEvent
    public void onChatReceive(ClientChatReceivedEvent event) {
        if (Settings.toggled) {
            if (event.type == 2) {
                if (Settings.messageEnabled) {
                    event.message = new ChatComponentText(Settings.getMessage());
                } else {
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent event) {
        if(settings.hotkey.isPressed()) {
            Settings.toggled = !Settings.toggled;
            settings.saveCfg();
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("\u00A7c[\u00A7rActionBarHider\u00A7c] Mod toggled " + (Settings.toggled ? "\u00A7aON" : "\u00A7cOFF")));
        }
    }

    public Settings getSettings() {
        return settings;
    }
}
