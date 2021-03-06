package dev.odod.actionbarhider.commands;

import dev.odod.actionbarhider.ActionBarHider;
import dev.odod.actionbarhider.config.Settings;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.Collections;
import java.util.List;

public class CommandActionBarHider extends CommandBase {

    private ActionBarHider mod;

    public CommandActionBarHider(ActionBarHider mod) {
        this.mod = mod;
    }

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
        return "\u00A7c/" + getCommandName() + " <toggle/message/messagetoggle>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    private String prefix = "\u00A7c[\u00A7rActionBarHider\u00A7c] ";

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
                if (args.length < 2) {
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("\u00A7c/abh msg <new message>"));
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        sb.append(args[i]).append(" ");
                    }
                    String msg = sb.toString().trim();
                    modMessage(msg);
                }
            }

            else if (args[0].equalsIgnoreCase("msg")) {
                if (args.length < 2) {
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("\u00A7c/abh msg <new message>"));
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        sb.append(args[i]).append(" ");
                    }
                    String msg = sb.toString().trim();
                    modMessage(msg);
                }
            }

            else if (args[0].equalsIgnoreCase("messagetoggle")) {
                modMessageToggle();
            }

            else if (args[0].equalsIgnoreCase("msgtoggle")) {
                modMessageToggle();
            }

            else if (args[0].equalsIgnoreCase("msgt")) {
                modMessageToggle();
            }

        } else {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
        }


    }

    public void modToggle() {
        Settings.toggled = !Settings.toggled;
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(prefix + "Mod toggled " + (Settings.toggled ? "\u00A7aON" : "\u00A7cOFF")));
        mod.getSettings().saveCfg();
    }

    private void modMessage(String msg) {

        if (msg.contains("&")) {
            msg = msg.replace("&", "\u00a7");
            Settings.setMessage(msg);
        } Settings.setMessage(msg);
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(prefix + "Custom message set to " + msg));
        mod.getSettings().saveCfg();

    }

    private void modMessageToggle() {
        Settings.messageEnabled = !Settings.messageEnabled;
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(prefix + "Custom message toggled " + (Settings.messageEnabled ? "\u00A7aON" : "\u00A7cOFF")));
        mod.getSettings().saveCfg();
    }
}
