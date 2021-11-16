package dev.rgd.api.chat;

import dev.rgd.api.text.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@SuppressWarnings("ALL")
public class Title {

    public Title(String title, String subtitle, int fadeIn, int hold, int fadeOut){ // full title.
        for (Player player : Bukkit.getOnlinePlayers()){
            player.sendTitle(title, subtitle, fadeIn, hold, fadeOut);
        }
    }
    public Title(String title, int fadeIn, int hold, int fadeOut){ // only title, no subtitle.
        for (Player player : Bukkit.getOnlinePlayers()){
            player.sendTitle(title,"", fadeIn, hold, fadeOut);
            String text = Text.SplitToComponentTimes(2);
        }
    }
}