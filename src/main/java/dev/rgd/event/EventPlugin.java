package dev.rgd.event;

import dev.rgd.api.teams.EventTeams;
import dev.rgd.api.teams.Teams;
import dev.rgd.event.opCommands.TimerCommand;
import dev.rgd.event.scoreboard.outOfGame.HubScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class EventPlugin extends JavaPlugin implements Listener {
    private static EventPlugin instance;
    public boolean pauseTimer = false;
    public int addToTimer = 0;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();//creates config.yml
        // Plugin startup logic
        instance = this;

        System.out.println("starting..");

        Objects.requireNonNull(getCommand("timer")).setExecutor(new TimerCommand());

        getServer().getPluginManager().registerEvents(this,this);

        Teams teams = new Teams();
        teams.addPlayer(Bukkit.getPlayer("Sappermine"), EventTeams.orange);

        /*add Players to teams like this, obviously not me or bobbie. Customisation of teams is handled separately.

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
        HubScoreboard hubScoreboard = new HubScoreboard();
        hubScoreboard.createScoreboard(event.getPlayer());
        hubScoreboard.setTimer(40);
        event.getPlayer().sendMessage("Welcome!");
    }
}