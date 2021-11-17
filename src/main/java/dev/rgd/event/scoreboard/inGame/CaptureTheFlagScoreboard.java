package dev.rgd.event.scoreboard.inGame;

import dev.rgd.api.points.Points;
import dev.rgd.api.text.Text;
import dev.rgd.event.EventPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class CaptureTheFlagScoreboard {
    private final EventPlugin plugin = EventPlugin.getInstance();
    private final Points points = new Points();
    int time = 0;

    public void createScoreboard(Player player) {

        Scoreboard scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective(player.getName().substring(0,4) + "Hub", "dummy","Name TBD");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Team timer = scoreboard.registerNewTeam("timer");
        timer.addEntry(" ");
        timer.setPrefix(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Starting in: ");
        timer.setSuffix(ChatColor.MAGIC.toString());
        objective.getScore(" ").setScore(15);

        objective.getScore(ChatColor.AQUA + "Map: insert map name here").setScore(14);

        objective.getScore(" ").setScore(13);// blank line

        Team round = scoreboard.registerNewTeam("round");
        round.addEntry(ChatColor.AQUA + "Round: ");
        round.setSuffix(ChatColor.MAGIC.toString());
        objective.getScore(ChatColor.GREEN + "Round: ").setScore(12);

        objective.getScore("  ").setScore(11);// blank line

        Team score = scoreboard.registerNewTeam("score");
        score.addEntry(ChatColor.GOLD.toString() + ChatColor.BOLD + "Score: " + ChatColor.WHITE);
        score.setSuffix(points.getScore(player).toString());
        objective.getScore(ChatColor.GOLD.toString() + ChatColor.BOLD + "Score: " + ChatColor.WHITE).setScore(10);

        player.setScoreboard(scoreboard);
    }

    //These methods change the description for the timer.
    public void startingInMessage(){
        Bukkit.getOnlinePlayers().forEach(player -> Objects.requireNonNull(player.getScoreboard().getTeam("timer")).setPrefix(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Starting in:"));
    }
    public void timeLeftMessage(){
        Bukkit.getOnlinePlayers().forEach(player -> Objects.requireNonNull(player.getScoreboard().getTeam("timer")).setPrefix(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Time left:"));
    }
    public void nextRoundInMessage(){
        Bukkit.getOnlinePlayers().forEach(player -> Objects.requireNonNull(player.getScoreboard().getTeam("timer")).setPrefix(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Next round:"));
    }
    //this method will take an int and tick down the scoreboard every second from the int until it hits -1.
    public void setTimer(int seconds){
        this.time = seconds;
        new BukkitRunnable(){
            @Override
            public void run() {
                if (plugin.pauseTimer)return;
                if (plugin.addToTimer != 0){
                    time += plugin.addToTimer;
                    plugin.addToTimer = 0;
                }

                if (time == 0)this.cancel();
                Bukkit.getOnlinePlayers().forEach(player -> Objects.requireNonNull(player.getScoreboard().getTeam("timer")).setSuffix(Text.SplitToComponentTimes(time)));
                time -= 1;
            }
        }.runTaskTimer(EventPlugin.getInstance(),0,20);
    }

    /*
    name tbd
    {message} {time}    15
    map: "map"          14
                        13
    Round: {round}      12
                        11
    Score: {score}      10

     */
}
