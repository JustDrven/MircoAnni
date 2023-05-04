package dev.justdrven.microanni.listeners;

import dev.justdrven.microanni.Main;
import dev.justdrven.microanni.game.player.GamePlayer;
import dev.justdrven.microanni.game.state.State;
import dev.justdrven.microanni.utils.Colors;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerListener implements Listener {

    private final Main pl;

    public PlayerListener(Main pl) {
        this.pl = pl;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void join(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        final Player p = e.getPlayer();
        pl.getGame().getPlayerManager().add(p.getUniqueId());
        pl.getGame().getPlayerManager().clear(p);
        pl.getGame().getPlayerManager().update();
        if (pl.getGame().getState() == State.LOBBY) {
            pl.getGame().GameChecktoStart();
            p.teleport(pl.getGame().getMapManager().getLobby());
            pl.getGame().getPlayerManager().getPlayers().stream()
                    .filter(players -> !players.isSpectating())
                    .forEach(ps -> {
                        ps.getBukkitPlayer().sendMessage(Colors.format("&8[&bServer&8] &3" + p.getName() + "&f has joined the game. &3("
                                + pl.getGame().getPlayerManager().getPlayers().size() + ")"));
                    });
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void left(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        final Player p = e.getPlayer();
        pl.getGame().getPlayerManager().remove(p.getUniqueId());
        pl.getGame().getPlayerManager().update();
        if (pl.getGame().getState() == State.LOBBY) {
            pl.getGame().GameChecktoStart();
            pl.getGame().getPlayerManager().getPlayers().stream()
                    .filter(players -> !players.isSpectating())
                    .forEach(ps -> {
                        ps.getBukkitPlayer().sendMessage(Colors.format("&8[&bServer&8] &3" + p.getName() + "&f left the game. &3("
                                + pl.getGame().getPlayerManager().getPlayers().size() + ")"));
                    });
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void death(PlayerDeathEvent e) {
        e.setDeathMessage(null);
        Player killer = (Player) e.getEntity().getKiller();
        Player victim = (Player) e.getEntity();
        final GamePlayer GameVictim = pl.getGame().getPlayerManager().getPlayer(victim.getUniqueId()).get();
        if (pl.getGame().getState() == State.INGAME) {
            pl.getGame().getPlayerManager().remove(victim.getUniqueId());
            if (killer instanceof Player && victim instanceof Player) {
                pl.getGame().getPlayerManager().getPlayers().forEach(ps -> {
                    ps.getBukkitPlayer().sendMessage(Colors.format("&8[&bServer&8] &b" + victim.getName() + "&f was killed by: &3" + killer.getName()));
                });
            }
            pl.getGame().checkPlayersToEnd();
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                victim.spigot().respawn();
            }
        }.runTaskLater(pl, 5L);


        GameVictim.setSpectating();


    }

}
