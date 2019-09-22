package dev.odod.actionbarhider;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "actionbarhider", version = "v1.0.0")
public class ActionBarHider {

    public static boolean toggled = true;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new CommandActionBarHider());
    }

    @SubscribeEvent
    public void onChatReceive(ClientChatReceivedEvent e) {
        if (toggled) {
            if (e.type == 2) {
                e.setCanceled(true);
            }
        }
    }
}
