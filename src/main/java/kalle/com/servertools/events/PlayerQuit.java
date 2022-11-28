package kalle.com.servertools.events;

import kalle.com.servertools.commands.VanishCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        VanishCommand.stopInvisibility(player);
        VanishCommand.invisiblePlayers.remove(player);
    }

}
