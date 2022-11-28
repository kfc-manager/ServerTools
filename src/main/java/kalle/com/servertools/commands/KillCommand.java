package kalle.com.servertools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("kill")) {
            if (args.length > 1) { sender.sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.RED + " Too many arguments!"); return false; } //too many arguments are passed
            if (args.length < 1) {
                if (sender instanceof Player) { //sender is a player
                    Player player = (Player) sender;
                    player.setHealth(0); //kill player
                    return true;
                }
                sender.sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.RED + " The command requires an argument!"); //sender is not a player and an argument is required
                return false;
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getName().equals(args[0])) {
                    player.setHealth(0); //kill player
                    sender.sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.GREEN + " Player '" + player.getName() + "' has been killed!");
                    return true;
                }
            }
            sender.sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.RED + " The player '" + args[0] + "' is not online!");
            return true;
        }
        return false;
    }

}
