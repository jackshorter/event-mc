package dev;

import org.bukkit.plugin.java.JavaPlugin;

public final class event extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("starting...");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
