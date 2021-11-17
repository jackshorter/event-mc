package dev.rgd.event.scoreboard.outOfGame;

import dev.rgd.api.points.Points;
import dev.rgd.api.teams.Teams;
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

public class HubScoreboard {
    private final Teams teams = new Teams();
    private final Points points = new Points();
    public int time = 0;

    public void createScoreboard(Player player){

        Scoreboard scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective(player.getName().substring(0,4) + "Hub", "dummy", "Name TBD");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Team timer = scoreboard.registerNewTeam("timer");
        timer.addEntry(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Starting in: ");
        timer.setSuffix(ChatColor.MAGIC.toString());
        objective.getScore(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Starting in: ").setScore(15);

        objective.getScore(" ").setScore(14);// blank line

        objective.getScore(ChatColor.BOLD + "Your Team:").setScore(13);
        objective.getScore(teams.getTeamChatColor(player) + teams.getTeamDisplayName(player)).setScore(12);

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
                if (time == 0)this.cancel();
                Bukkit.getOnlinePlayers().forEach(player -> Objects.requireNonNull(player.getScoreboard().getTeam("timer")).setSuffix(Text.SplitToComponentTimes(time)));
                time -= 1;
            }
        }.runTaskTimer(EventPlugin.getInstance(),0,20);
    }

    /*
    name tbd
    starting in: {time} 15
                        14
    Your team:          13
    {team}              12
                        11
    Score: {score}      10

     */

}