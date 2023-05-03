package dev.justdrven.microanni.game.state.tasks;

import dev.justdrven.microanni.game.Game;
import dev.justdrven.microanni.game.state.State;

import dev.justdrven.microanni.utils.Colors;
import dev.justdrven.microanni.utils.Titles;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

public class GameStartTask extends BukkitRunnable {

    public int time = 10;
    private final Game game;

    public GameStartTask(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        if (game.getState() != State.LOBBY) return;
        if (time == 10 || time == 3 || time == 2) {
            game.getPlayerManager().getPlayers().stream()
                    .filter(gamePlayers -> !gamePlayers.isSpectating()).forEach(players -> {
                        Titles.send(players.getBukkitPlayer(), Colors.format("&3&l" + time),
                                Colors.format("&fGame start in"));

                        players.getBukkitPlayer().playSound(players.getBukkitPlayer().getLocation(),
                                Sound.ORB_PICKUP, 1.0F, 1.0F);
                    });
        }
        if (time == 1) {
            game.getPlayerManager().getPlayers().stream()
                    .filter(gamePlayers -> !gamePlayers.isSpectating()).forEach(players -> {
                        Titles.send(players.getBukkitPlayer(), Colors.format("&3&l" + time),
                                Colors.format("&fGame start in"));

                        players.getBukkitPlayer().playSound(players.getBukkitPlayer().getLocation(),
                                Sound.ORB_PICKUP, 1.0F, 1.0F);

                    });
        }
        if (time == 0) {

            game.getPlayerManager().getPlayers().stream()
                    .filter(gamePlayers -> !gamePlayers.isSpectating()).forEach(players -> {
                        Titles.send(players.getBukkitPlayer(), Colors.format("&3&l  &e&l   "),
                                Colors.format("&a&oGame started!"));

                        players.getBukkitPlayer().sendMessage(Colors.format("&8[&bServer&8] &a&oGame started!"));

                        players.getBukkitPlayer().playSound(players.getBukkitPlayer().getLocation(),
                                Sound.ORB_PICKUP, 1.0F, 1.0F);
                    });

            game.setState(State.INGAME);
            cancel();
        }

        time--;
    }

}
