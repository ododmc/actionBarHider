package dev.odod.actionbarhider;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.Collections;
import java.util.List;

public class CommandActionBarHider extends CommandBase {


    @Override
    public String getCommandName() {
        return "actionbarhider";
    }

    @Override
    public List<String> getCommandAliases() {
        return Collections.singletonList("abh");
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName() + " - toggles the mod";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    private String prefix = EnumChatFormatting.RED + "[" + EnumChatFormatting.WHITE + "ActionBarHider" + EnumChatFormatting.RED + "] ";

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("toggle")) {
                modToggle();
            }

            else if (args[0].equalsIgnoreCase("t")) {
                modToggle();
            }

            else if (args[0].equalsIgnoreCase("message")) {
                modMessage();
            }

            else if (args[0].equalsIgnoreCase("msg")) {
                modMessage();
            }
        }

    }

    public void modToggle() {
        ActionBarHider.toggled = !ActionBarHider.toggled;
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(prefix + "Toggled " + (ActionBarHider.toggled ? "\u00A7aON" : "\u00A7cOFF")));

    }

    private void modMessage() {

    }
}
