package dev.rgd.event.scoreboard.outOfGame;

import dev.rgd.api.points.Points;
import dev.rgd.api.teams.Teams;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class HubScoreboard {
    Teams teams = new Teams();
    Points points = new Points();

    public void createScoreboard(Player player){

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective(player.getName().substring(0,4) + "Hub", "dummy");
        objective.setDisplayName("Name TBD");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Team timer = scoreboard.registerNewTeam("timer");
        timer.addEntry("");
        timer.setPrefix(ChatColor.GOLD + "Starting in: " + ChatColor.MAGIC);
        objective.getScore("").setScore(15);

        objective.getScore(" ").setScore(14);// blank line

        objective.getScore(ChatColor.BOLD + "Your Team:").setScore(12);
        objective.getScore(teams.getTeamChatColor(player) + teams.getTeamDisplayName(player)).setScore(11);

        objective.getScore("  ").setScore(10);// blank line

        Team score = scoreboard.registerNewTeam("score");
        score.addEntry(ChatColor.GOLD.toString() + ChatColor.BOLD + "Score: " + ChatColor.WHITE);
        score.setSuffix(points.getScore(player).toString());
        objective.getScore(ChatColor.GOLD.toString() + ChatColor.BOLD + "Score: " + ChatColor.WHITE).setScore(8);

        player.setScoreboard(scoreboard);
    }

}