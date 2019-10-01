package dev.odod.actionbarhider;

import dev.odod.actionbarhider.commands.CommandActionBarHider;
import dev.odod.actionbarhider.config.Settings;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "actionbarhider", version = "v1.1.0")
public class ActionBarHider {

    private Settings settings = new Settings();

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new CommandActionBarHider(this));
        settings.loadCfg();
    }

    @SubscribeEvent
    public void onChatReceive(ClientChatReceivedEvent e) {
        if (Settings.toggled) {
            if (e.type == 2) {
                if (Settings.messageEnabled) {
                    e.message = new ChatComponentText(Settings.getMessage());
                } else {
                    e.setCanceled(true);
                }
            }
        }
    }

    public Settings getSettings() {
        return settings;
    }
}
