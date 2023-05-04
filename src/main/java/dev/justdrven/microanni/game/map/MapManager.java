package dev.justdrven.microanni.game.map;

import dev.justdrven.microanni.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class MapManager {

    private final Main pl;
    private Location lobby;
    private Location end;

    public MapManager(Main pl) {
        this.pl = pl;

        // Lobby location
        String worldName = pl.getLocation().getConfig().getString("game.spawns.lobby.name");
        double lobbyx = pl.getLocation().getConfig().getDouble("game.spawns.lobby.x");
        double lobbyy = pl.getLocation().getConfig().getDouble("game.spawns.lobby.y");
        double lobbyz = pl.getLocation().getConfig().getDouble("game.spawns.lobby.z");
        float lobbypitch = (float) pl.getLocation().getConfig().getDouble("game.spawns.lobby.pitch");
        float lobbyyaw = (float) pl.getLocation().getConfig().getDouble("game.spawns.lobby.yaw");

        this.lobby = new Location(Bukkit.getWorld(worldName),
                lobbyx,
                lobbyy,
                lobbyz);
        this.lobby.setYaw(lobbyyaw);
        this.lobby.setPitch(lobbypitch);

        // End location

        String endWorld = pl.getLocation().getConfig().getString("game.spawns.end.name");
        double endx = pl.getLocation().getConfig().getDouble("game.spawns.end.x");
        double endy = pl.getLocation().getConfig().getDouble("game.spawns.end.y");
        double endz = pl.getLocation().getConfig().getDouble("game.spawns.end.z");
        float endpitch = (float) pl.getLocation().getConfig().getDouble("game.spawns.end.pitch");
        float endyaw = (float) pl.getLocation().getConfig().getDouble("game.spawns.end.yaw");

        this.end = new Location(Bukkit.getWorld(endWorld),
                endx,
                endy,
                endz);
        this.end.setYaw(endyaw);
        this.end.setPitch(endpitch);

    }

    public Location getLobby() {
        return lobby;
    }

    public Location getEnd() {
        return end;
    }
}
