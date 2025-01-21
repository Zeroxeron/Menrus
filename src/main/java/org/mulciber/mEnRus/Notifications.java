package org.mulciber.mEnRus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class Notifications {
    static final Logger log = Logger.getLogger("Minecraft");

    static Boolean debug = MEnRus.getInstance().getConfig().getBoolean("Debug");

    static String prefix = ChatColor.RED + MEnRus.getInstance().getConfig().getString("Prefix") + ChatColor.DARK_GRAY + " >" + ChatColor.GRAY + " ";

    public static void CreateNotification(String msg, int mode) {
        switch (mode) {
            case 0:
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.hasPermission("enrus.notify"))
                        player.sendMessage(prefix + msg);
                }
                return;
            case 1:
                log.info(prefix + msg);
                return;
            case 2:
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.hasPermission("enrus.notify"))
                        player.sendMessage(prefix + msg);
                }
                log.info(prefix + msg);
                return;
            case 3:
                if (debug)
                    log.info(prefix + msg);
                return;
        }
        log.warning(prefix + "Wrong message mode used:" + mode);
        log.info(prefix + msg);
    }

    public static void SendMsg(CommandSender sender, String msg) {
        sender.sendMessage(prefix + msg);
    }

    public static String getPrefix() {
        return prefix;
    }

    public static boolean getDebug() {
        return debug;
    }
}