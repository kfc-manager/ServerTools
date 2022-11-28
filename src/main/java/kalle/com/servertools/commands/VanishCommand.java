package kalle.com.servertools.commands;

import kalle.com.servertools.ServerTools;
import kalle.com.servertools.config.VanishedPlayersConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class VanishCommand implements CommandExecutor {

    public static HashMap<Player, Integer> invisiblePlayers = new HashMap<>(); //public so we can remove from PlayerQuitEvent TODO make it private

    private static ServerTools plugin;

    public VanishCommand(ServerTools plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("vanish")) {
            if (args.length > 1) { sender.sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.RED + " Too many arguments!"); return false; } //too many arguments are passed
            if (args.length < 1) {
                if (sender instanceof Player) { //sender is a player
                    Player player = (Player) sender;
                    VanishedPlayersConfiguration.toggleInvisibility(player); //save changes in config
                    if (vanish(player)) sender.sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.GREEN + " You are now vanished!");
                    else sender.sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.GREEN + " You are no longer vanished!");
                    return true;
                }
                sender.sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.RED + " The command requires an argument!"); //sender is not a player and an argument is required
                return false;
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getName().equals(args[0])) {
                    VanishedPlayersConfiguration.toggleInvisibility(player); //save changes in config
                    if (vanish(player)) sender.sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.GREEN + " Player '" + player.getName() + "' has been vanished!");
                    else sender.sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.GREEN + " Player '" + player.getName() + "' is no longer vanished!");
                    return true;
                }
            }
            sender.sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.RED + " The player '" + args[0] + "' is not online!");
            return true;
        }
        return false;
    }

    public static boolean vanish(Player player) {
        if (!invisiblePlayers.containsKey(player)) {
            //TODO let player vanish
            try {
                invisiblePlayers.put(player, startInvisibility(player));
            } catch (IllegalArgumentException e) {
                //there is already a thread for the player to keep him invisible
            }
            return true;
        }
        //TODO player should not be vanished anymore
        stopInvisibility(player);
        invisiblePlayers.remove(player);
        return false;
    }

    private static int startInvisibility(Player player) {
        if (invisiblePlayers.containsKey(player)) throw new IllegalArgumentException();
        int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,140,0));
        },0L,60L);
        return taskID;
    }

    public static void stopInvisibility(Player player) { //public so we can remove from the PlayerQuitEvent TODO make it private
        if (invisiblePlayers.get(player) == null) return;
        int taskID = invisiblePlayers.get(player);
        Bukkit.getScheduler().cancelTask(taskID);
    }


}
