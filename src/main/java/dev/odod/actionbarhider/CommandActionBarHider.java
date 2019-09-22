package dev.odod.actionbarhider;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CommandActionBarHider extends CommandBase {
    @Override
    public String getCommandName() {
        return "actionbarhider";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName() + " - toggles action bar being visible";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        ActionBarHider.toggled = !ActionBarHider.toggled;
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("[ABH] Toggled " + (ActionBarHider.toggled ? "ON" : "OFF")));
    }
}
