package dev.justdrven.microanni.game.state.tasks;

import dev.justdrven.microanni.game.Game;
import dev.justdrven.microanni.game.state.State;

public class TaskManager {

    private Game game;
    private GameStartTask gameStartTask;

    public TaskManager(Game game) {
        this.game = game;
        this.gameStartTask = new GameStartTask(game);
    }

    public GameStartTask getGameStartTask() {
        return gameStartTask;
    }

    public void start() {
        if (game.getPlayerManager().getPlayers().size() >= game.getPlugin().getConfig().getInt("players-to-start")
        && game.getState() == State.LOBBY) {
            this.getGameStartTask().runTaskTimer(game.getPlugin(), 0L, 20L);
        }
        return;
    }

    public Game getGame() {
        return game;
    }
}
