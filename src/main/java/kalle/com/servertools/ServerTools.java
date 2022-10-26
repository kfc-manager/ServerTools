package kalle.com.servertools;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class ServerTools extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.GREEN + " Plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.RED + " Plugin has been disabled!");
    }

    private void registerCommands() {

    }


}
