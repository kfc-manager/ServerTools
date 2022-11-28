package kalle.com.servertools.config;

import kalle.com.servertools.ServerTools;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class VanishedPlayersConfiguration {

    private static ServerTools plugin;

    private static FileConfiguration config;
    private static File file;

    public VanishedPlayersConfiguration(ServerTools plugin) {
        this.plugin = plugin;
    }

    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        file = new File(plugin.getDataFolder(), "invisible.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                plugin.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.GREEN + " invisible.yml file has been created!");
            } catch (IOException e) {
                plugin.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.RED + " CONFIG ERROR: could not create invisible.yml file!");
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    private static void save() {
        try {
            config.save(file);
            config = YamlConfiguration.loadConfiguration(file);
        } catch (IOException e) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.RED + " CONFIG ERROR: could not save invisible.yml file!");
        }
    }

    public static void toggleInvisibility(Player player) {
        String uuid = player.getUniqueId().toString();
        if (isInvisible(player)) {
            config.set(uuid, false);
        } else config.set(uuid,true);
        save();
    }

    public static boolean isInvisible(Player player) {
        String uuid = player.getUniqueId().toString();
        Boolean invisible = config.getBoolean(uuid);
        if (invisible == null) return false;
        return invisible;
    }

}
