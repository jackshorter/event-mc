package dev.rgd.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class EventPlugin extends JavaPlugin implements Listener {
    private static EventPlugin instance;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();//creates config.yml
        // Plugin startup logic
        instance = this;

        System.out.println("starting..");

        /*add Players to teams like this, obviously not me or bobbie. Customisation of teams will be handled separately.

        Teams teams = new Teams();
        teams.addPlayer(Bukkit.getPlayer("sappermine"),TeamEnum.red);
        teams.addPlayer(Bukkit.getPlayer("bobcatplays"),TeamEnum.aqua);

        */

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static EventPlugin getInstance() {
        return instance;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.getPlayer().sendMessage("Welcome!");
    }
}