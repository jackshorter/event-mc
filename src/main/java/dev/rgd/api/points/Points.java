package dev.rgd.api.points;

import dev.rgd.event.EventPlugin;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Points {//everything here untested because its different to the original code because the original code is stupid.
    EventPlugin plugin = EventPlugin.getInstance();

    private final HashMap<String, Integer> playerScores = new HashMap<>();

    public void addToScore(Player player, int score){
        getScoreS();
        String playerUUID = player.getUniqueId().toString();
        if (!playerScores.containsKey(playerUUID)){
            playerScores.put(playerUUID,0); // if player doesn't have a score yet, this puts 0 into the hashmap to avoid any null errors.
        }
        playerScores.put(playerUUID, playerScores.get(playerUUID) + score); // put new score into hashmap.
        saveScores();
    }

    public Integer getScore(Player player){
        if (!playerScores.containsKey(player.getUniqueId().toString())){
            return 0; // returns 0 if player doesn't have a score in the hashmap to avoid null errors.
        }
        return playerScores.get(player.getUniqueId().toString());
    }

    private void saveScores(){
        for (Map.Entry<String,Integer> entry : playerScores.entrySet()){
            plugin.getConfig().set("data." + entry.getKey(), entry.getValue());
        }
        plugin.saveConfig();
    }

    private void getScoreS(){
        plugin.getConfig().getConfigurationSection("data").getKeys(false).forEach(key -> playerScores.put(key, (Integer) plugin.getConfig().get("data." + key)));
    }
}