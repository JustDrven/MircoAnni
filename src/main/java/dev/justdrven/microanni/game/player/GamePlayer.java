package dev.justdrven.microanni.game.player;

import dev.justdrven.microanni.utils.Colors;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GamePlayer {

    private final UUID uuid;
    private boolean spectating = false;

    public GamePlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public Player getBukkitPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }

    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(this.uuid);
    }

    public boolean isOnline() {
        return this.getOfflinePlayer().isOnline();
    }

    public void setSpectating() {
        this.spectating = true;

        for (Player ps : Bukkit.getOnlinePlayers()) {
            if (ps.getUniqueId().equals(this.uuid)) continue;

            ps.hidePlayer(this.getBukkitPlayer());
        }
        
        this.getBukkitPlayer().setAllowFlight(true);
        this.getBukkitPlayer().setFlying(true);
        this.getBukkitPlayer().setGameMode(GameMode.ADVENTURE);
        this.getBukkitPlayer().sendMessage(Colors.format("&8[&bServer&8] &fYou are spectator!"));

    }

    public UUID getUUID() {
        return uuid;
    }

    public boolean isSpectating() {
        return spectating;
    }
}
