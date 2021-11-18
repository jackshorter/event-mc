package dev.rgd.api.teams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

//@SuppressWarnings("ALL")
public class Teams {

    Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

    public void registerTeams() {
        try {
            scoreboard.registerNewTeam("spectator");
        }catch (IllegalArgumentException ignored){}
        try {
            scoreboard.registerNewTeam("red");
        }catch (IllegalArgumentException ignored){}
        try {
            scoreboard.registerNewTeam("orange");
        }catch (IllegalArgumentException ignored){}
        try {
            scoreboard.registerNewTeam("yellow");
        }catch (IllegalArgumentException ignored){}
        try {
            scoreboard.registerNewTeam("green");
        }catch (IllegalArgumentException ignored){}
        try {
            scoreboard.registerNewTeam("lime");
        }catch (IllegalArgumentException ignored){}
        try {
            scoreboard.registerNewTeam("blue");
        }catch (IllegalArgumentException ignored){}
        try {
            scoreboard.registerNewTeam("aqua");
        }catch (IllegalArgumentException ignored){}
        try {
            scoreboard.registerNewTeam("purple");
        }catch (IllegalArgumentException ignored){}
        try {
            scoreboard.registerNewTeam("pink");
        }catch (IllegalArgumentException ignored){}
        try {
            scoreboard.registerNewTeam("white");
        }catch (IllegalArgumentException ignored){}
        try {
            scoreboard.registerNewTeam("gray");
        }catch (IllegalArgumentException ignored){}
    } // attempts to register teams. In try catch blocks because it can only be run successfully once.

    public void TeamProperties(){ // sets properties we see in game like display name, color and prefix.
        String openBracket = ChatColor.WHITE + "[";
        String closeBracket = ChatColor.WHITE + "] ";

        Team spectator = scoreboard.getTeam(EventTeams.spectator.name());
        if (spectator == null)return;
        spectator.setDisplayName("Spectator");
        spectator.setPrefix(openBracket + "Spectator" + closeBracket);
        spectator.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

        Team red = scoreboard.getTeam(EventTeams.red.name());
        if (red == null)return;
        red.setDisplayName("Red");
        red.setColor(ChatColor.RED);
        red.setPrefix(openBracket + "Spectator" + closeBracket);
        red.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

        Team orange = scoreboard.getTeam(EventTeams.orange.name());
        if (orange == null)return;
        orange.setDisplayName("Orange");
        orange.setColor(ChatColor.GOLD);
        orange.setPrefix(openBracket + ChatColor.GOLD + "ORANGE" + closeBracket);
        orange.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

        Team yellow = scoreboard.getTeam(EventTeams.yellow.name());
        if (yellow == null)return;
        yellow.setDisplayName("Yellow");
        yellow.setColor(ChatColor.YELLOW);
        yellow.setPrefix(openBracket + ChatColor.YELLOW + "YELLOW" + closeBracket);
        yellow.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

        Team green = scoreboard.getTeam(EventTeams.green.name());
        if (green == null)return;
        green.setDisplayName("Green");
        green.setColor(ChatColor.DARK_GREEN);
        green.setPrefix(openBracket + ChatColor.DARK_GREEN + "GREEN" + closeBracket);
        green.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

        Team lime = scoreboard.getTeam(EventTeams.lime.name());
        if (lime == null)return;
        lime.setDisplayName("Lime");
        lime.setColor(ChatColor.GREEN);
        lime.setPrefix(openBracket + ChatColor.GREEN + "LIME" + closeBracket);
        lime.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

        Team blue = scoreboard.getTeam(EventTeams.blue.name());
        if (blue == null)return;
        blue.setDisplayName("Blue");
        blue.setColor(ChatColor.BLUE);
        blue.setPrefix(openBracket + ChatColor.BLUE + "BLUE" + closeBracket);
        blue.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

        Team aqua = scoreboard.getTeam(EventTeams.aqua.name());
        if (aqua == null)return;
        aqua.setDisplayName("Teal");
        aqua.setColor(ChatColor.AQUA);
        aqua.setPrefix(openBracket + ChatColor.AQUA + "TEAL" + closeBracket);
        aqua.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

        Team purple = scoreboard.getTeam(EventTeams.purple.name());
        if (purple == null)return;
        purple.setDisplayName("Purple");
        purple.setColor(ChatColor.DARK_PURPLE);
        purple.setPrefix(openBracket + ChatColor.DARK_PURPLE + "PURPLE" + closeBracket);
        purple.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

        Team pink = scoreboard.getTeam(EventTeams.pink.name());
        if (pink == null)return;
        pink.setDisplayName("Pink");
        pink.setColor(ChatColor.LIGHT_PURPLE);
        pink.setPrefix(openBracket + ChatColor.LIGHT_PURPLE + "PINK" + closeBracket);
        pink.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

        Team white = scoreboard.getTeam(EventTeams.white.name());
        if (white == null)return;
        white.setDisplayName("White");
        white.setColor(ChatColor.WHITE);
        white.setPrefix(openBracket + "WHITE" + closeBracket);
        white.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

        Team gray = scoreboard.getTeam(EventTeams.gray.name());
        if (gray == null)return;
        gray.setDisplayName("Grey");
        gray.setColor(ChatColor.GRAY);
        gray.setPrefix(openBracket + ChatColor.GRAY + "GRAY" + closeBracket);
        gray.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

    }

    public void addPlayer(Player player, EventTeams team){
        for (Team teams : scoreboard.getTeams()){
            if (!teams.getName().equals(team.name())) return;
            teams.addEntry(player.toString());
        }
    }
    public Team getTeam(Player player){
        for (Team team : scoreboard.getTeams()){
            if (team.getEntries().contains(player.getName())){
                return team;
            }
        }
        return null;
    }
    public String getTeamDisplayName(Player player){
        for (Team team : scoreboard.getTeams()){
            if (team.getEntries().contains(player.getName())){
                return team.getDisplayName();
            }
        }
        return "No Team!";
    }
    public ChatColor getTeamChatColor(Player player){
        for (Team team : scoreboard.getTeams()){
            if (team.getEntries().contains(player.getName())){
                return team.getColor();
            }
            return ChatColor.WHITE;
        }
        return ChatColor.WHITE;
    }

}