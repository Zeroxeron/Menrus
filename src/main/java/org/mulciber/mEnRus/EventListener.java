package org.mulciber.mEnRus;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.mulciber.mEnRus.methods.m1;

public class EventListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent e) {//todo AsyncChatEvent is not cancelled for some reason
        // Component message = e.message();
        // String msg = PlainTextComponentSerializer.plainText().serialize(message);
        String msg = e.getMessage();
        if (msg.equals(".") || msg.equals("..") || msg.equals("...")) return;
        if (msg.charAt(0) != '.') return;
        e.setCancelled(true);
        if (e.isAsynchronous()) {
            Bukkit.getScheduler().runTask(MEnRus.getInstance(), () -> e.getPlayer().chat(m1.replaceRussian(msg)));
        } else {
            e.getPlayer().chat(m1.replaceRussian(msg));
        }
    }

    @EventHandler
    public void onCmd(PlayerCommandPreprocessEvent e) {
        String msg = e.getMessage();
        if (msg.length() < 2)
            return;
        if (!m1.detectRussian(msg.charAt(1)))
            return;
        e.setCancelled(true);
        e.getPlayer().chat(m1.replaceRussian(msg));
    }
}
