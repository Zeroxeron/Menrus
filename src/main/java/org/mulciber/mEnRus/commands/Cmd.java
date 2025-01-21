package org.mulciber.mEnRus.commands;

import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mulciber.mEnRus.MEnRus;
import org.mulciber.mEnRus.Notifications;

import java.util.List;

import static org.mulciber.mEnRus.Notifications.SendMsg;

public class Cmd extends AbstractCmd {
    private static String MsgNoPerm = MEnRus.getInstance().getConfig().getString("messages.noPermission");

    public Cmd() {super("menrus");}

    public void execute(CommandSender sender, String label, String[] args) {
        if (args.length == 0) {
            if (!sender.hasPermission("menrus.admin")) {SendMsg(sender, MsgNoPerm); return;}
            SendMsg(sender, ChatColor.GOLD +""+ChatColor.BOLD+""+ChatColor.ITALIC +"MEnRus "+MEnRus.getInstance().getDescription().getVersion());
            SendMsg(sender, "/" + label + " reload");
            return;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("menrus.admin")) {SendMsg(sender, MsgNoPerm); return;}
            MEnRus.getInstance().reloadConfig();
            MsgNoPerm = MEnRus.getInstance().getConfig().getString("messages.noPermission");
            sender.sendMessage(Notifications.getPrefix() + "reloaded!");
            return;
        }
        if (args[0].equalsIgnoreCase("version")) {
            if (!sender.hasPermission("menrus.admin")) {SendMsg(sender, MsgNoPerm); return;}
            SendMsg(sender, ChatColor.GOLD +""+ChatColor.BOLD+""+ChatColor.ITALIC +"MEnRus "+MEnRus.getInstance().getDescription().getVersion());
            return;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(args.length == 1) return Lists.newArrayList("reload", "version");
        return Lists.newArrayList();
    }
}
