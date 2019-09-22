package dev.odod.actionbarhider;

import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;

@Mod(modid = ActionBarHider.MODID, version = ActionBarHider.VERSION)

public class ActionBarHider {

  static final String MODID = "actionbarhider";
  static final String VERSION = "v1.0.0";
  public static boolean toggled = true;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
      ClientCommandHandler.instance.registerCommand(new CommandActionBarHider());
      MinecraftForge.EVENT_BUS.register(this);
    }

    public void tick(TickEvent.ClientTickEvent e) {

    }

    private void getActionBar() {
      Field bar = ReflectionHelper.findField(GuiIngame.class, "field_73838_g", "recordPlaying");
      Field barCheck = ReflectionHelper.findField(GuiIngame.class, "field_73844_j", "recordIsPlaying");
      Field barTime = ReflectionHelper.findField(GuiIngame.class, "field_73845_h", "recordPlayingUpFor");

      
    }
}
