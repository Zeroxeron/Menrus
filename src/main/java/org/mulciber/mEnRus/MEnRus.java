package org.mulciber.mEnRus;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.mulciber.mEnRus.commands.Cmd;

public final class MEnRus extends JavaPlugin {
    private static MEnRus instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();
        new Cmd();
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
    }

    public static MEnRus getInstance() {
        return instance;
    }
}
