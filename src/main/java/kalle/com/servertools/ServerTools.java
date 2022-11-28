package kalle.com.servertools;

import kalle.com.servertools.commands.GamemodeCommand;
import kalle.com.servertools.commands.KillCommand;
import kalle.com.servertools.commands.VanishCommand;
import kalle.com.servertools.config.VanishedPlayersConfiguration;
import kalle.com.servertools.events.PlayerJoin;
import kalle.com.servertools.events.PlayerQuit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class ServerTools extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        VanishedPlayersConfiguration config = new VanishedPlayersConfiguration(this);
        config.setup();
        registerCommands();
        registerEvents();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (VanishedPlayersConfiguration.isInvisible(player))
                VanishCommand.vanish(player);
        }
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.GREEN + " Plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.RED + " Plugin has been disabled!");
    }

    private void registerCommands() {
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("kill").setExecutor(new KillCommand());
        getCommand("vanish").setExecutor(new VanishCommand(this));
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerJoin(),this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
    }


}
