package dev.justdrven.microanni.commands;

import dev.justdrven.microanni.Main;
import dev.justdrven.microanni.utils.Colors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameCommand implements CommandExecutor {

    private Main pl;

    public GameCommand(Main pl) {
        this.pl = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage(Colors.format("&8[&bServer&8] &fUsage: &a/game setlobby"));
                p.sendMessage(Colors.format("&8[&bServer&8] &fUsage: &a/game setend"));
                return false;
            }
            if (args[0].equalsIgnoreCase("setlobby")) {
                if (args.length == 1) {
                    pl.getConfig().set("game.spawns.lobby.name", p.getWorld().getName());
                    pl.getConfig().set("game.spawns.lobby.x", p.getLocation().getX());
                    pl.getConfig().set("game.spawns.lobby.y", p.getLocation().getY());
                    pl.getConfig().set("game.spawns.lobby.z", p.getLocation().getZ());
                    pl.getConfig().set("game.spawns.lobby.pitch", p.getLocation().getPitch());
                    pl.getConfig().set("game.spawns.lobby.yaw", p.getLocation().getYaw());

                    pl.saveConfig();
                    pl.reloadConfig();

                    p.sendMessage(Colors.format("&8[&bServer&8] &fYou're now set lobby location"));

                    return true;
                }
            }
            if (args[0].equalsIgnoreCase("setend")) {
                if (args.length == 1) {
                    pl.getConfig().set("game.spawns.end.name", p.getWorld().getName());
                    pl.getConfig().set("game.spawns.end.x", p.getLocation().getX());
                    pl.getConfig().set("game.spawns.end.y", p.getLocation().getY());
                    pl.getConfig().set("game.spawns.end.z", p.getLocation().getZ());
                    pl.getConfig().set("game.spawns.end.pitch", p.getLocation().getPitch());
                    pl.getConfig().set("game.spawns.end.yaw", p.getLocation().getYaw());

                    pl.saveConfig();
                    pl.reloadConfig();

                    p.sendMessage(Colors.format("&8[&bServer&8] &fYou're now set end location"));

                    return true;
                }
            } else {
                p.sendMessage(Colors.format("&8[&bServer&8] &fUsage: &a/game setlobby"));
                p.sendMessage(Colors.format("&8[&bServer&8] &fUsage: &a/game setend"));
                return false;
            }
        }
        return true;
    }
}
