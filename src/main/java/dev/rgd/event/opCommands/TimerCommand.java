package dev.rgd.event.opCommands;

import dev.rgd.event.EventPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.List;

public class TimerCommand implements TabExecutor {
    @Override
    public boolean onCommand(@Nullable CommandSender commandSender,@Nullable Command command,@Nullable String s,@Nullable String[] strings) {
        Player player = (Player) commandSender;
        if (player == null) return true;

        if (!player.isOp()) {
            player.sendMessage(ChatColor.DARK_RED + "Thought you could pull a sneaky one didn't you.");
            return true;
        }

        if (strings == null){
            player.sendMessage("Please use correct arguments");
            return true;
        }

        String arg1 = strings[0].toLowerCase();


        EventPlugin plugin =  EventPlugin.getInstance();
        switch (arg1) {
            case "pause":
                plugin.pauseTimer = true;
                break;
            case "resume":
                plugin.pauseTimer = false;
                break;
            case "add":
                plugin.setAddToTimer(plugin.getAddToTimer() + Integer.parseInt(strings[1]) );
                break;
        }


        return true;
    }

    @Override
    public List<String> onTabComplete(@Nullable CommandSender commandSender,@Nullable Command command,@Nullable String s,@Nullable String[] strings) {
        return null;
    }
}
