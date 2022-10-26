package kalle.com.servertools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("gamemode")) {
            if (args.length < 1) { sender.sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.RED + " The command requires an argument!"); return false; } //no arguments passed with the command
            if (args.length > 2) { sender.sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.RED + " Too many arguments!"); return false; } //too many arguments
            try {
                int gamemode = Integer.parseInt(args[0]); //integer which decides what gamemode the player gets set to
                Player target = null;
                if (args.length == 2) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.getName().equals(args[1])) {
                            target = player;
                            break;
                        }
                    }
                }
                if (args.length == 1) {
                    if (sender instanceof Player) {
                        target = (Player) sender; //sender is a player and one argument is passed, which means we need to change the gamemode of that player
                    } else { //sender is the console and need to pass two arguments
                        sender.sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.RED + " The command requires two arguments!");
                        return true;
                    }
                }
                if (gamemode < 0 || gamemode > 3) throw new NumberFormatException(); //integer is either smaller than 0 or bigger than 3
                if (gamemode == 0) target.setGameMode(GameMode.SURVIVAL); //0 stands for survival
                if (gamemode == 1) target.setGameMode(GameMode.CREATIVE); //1 stands for creative
                if (gamemode == 2) target.setGameMode(GameMode.ADVENTURE); //2 stands for adventure
                if (gamemode == 3) target.setGameMode(GameMode.SPECTATOR); //3 stands for spectator
                sender.sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.GREEN + " Gamemode of the player '" + target.getName() + "' has been changed!");
            } catch (NumberFormatException e) { //argument 0 is not in the right format
                sender.sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.RED + " The first argument must be an integer in the range of 0-3!");
            } catch (NullPointerException e) { //no online player was found with the right name
                sender.sendMessage(ChatColor.AQUA + "[ServerTools]" + ChatColor.RED + " The player '" + args[1] + "' is not online!");
            }
            return true;
        }
        return false;
    }

}
