package org.mulciber.mEnRus.commands;

import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mulciber.mEnRus.MEnRus;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCmd implements CommandExecutor, TabCompleter {

    public AbstractCmd(String command){
        PluginCommand pluginCommand = MEnRus.getInstance().getCommand(command);
        if(pluginCommand != null){
            pluginCommand.setExecutor(this);
            pluginCommand.setTabCompleter(this);
        }
    }

    public abstract void execute(CommandSender sender, String label, String[] args);

    public List<String> complete(CommandSender sender, String[] args){
        return null;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        execute(sender, label, args);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return filter(complete(sender, args), args);
    }

    private List<String> filter(List<String> list, String[] args){
        if(list == null) return null;
        String last = args[args.length - 1];
        List<String> result = new ArrayList<>();
        for(String arg : list){
            if(arg.toLowerCase().startsWith(last.toLowerCase())) result.add(arg);
        }
        return result;
    }
}
