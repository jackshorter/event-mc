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
        timer.setPrefix( "Starting in: " + StringUtils.repeat(ChatColor.MAGIC.toString(),3));
        objective.getScore("").setScore(15);

        objective.getScore(ChatColor.RESET.toString()).setScore(14);

        objective.getScore(ChatColor.BOLD + "Your Team:").setScore(12);
        objective.getScore(teams.getTeamChatColor(player) + teams.getTeamDisplayName(player)).setScore(11);
        objective.getScore(ChatColor.RESET.toString().toLowerCase()).setScore(10);

        Team score = scoreboard.registerNewTeam("score");
        score.addEntry(ChatColor.GOLD.toString() + ChatColor.BOLD + "Your score: " + ChatColor.WHITE);
        score.setPrefix(points.getScore(player).toString());
        objective.getScore("Your score: ").setScore(8);

        player.setScoreboard(scoreboard);
    }

}