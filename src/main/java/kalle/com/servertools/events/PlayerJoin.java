package kalle.com.servertools.events;

import kalle.com.servertools.commands.VanishCommand;
import kalle.com.servertools.config.VanishedPlayersConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (VanishedPlayersConfiguration.isInvisible(player)) VanishCommand.vanish(player);
    }

}
