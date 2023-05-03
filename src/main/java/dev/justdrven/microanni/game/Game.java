package dev.justdrven.microanni.game;

import dev.justdrven.microanni.Main;
import dev.justdrven.microanni.game.player.PlayerManager;
import dev.justdrven.microanni.game.state.State;
import dev.justdrven.microanni.game.state.tasks.TaskManager;

public class Game {

    private final Main pl;
    private final PlayerManager playerManager;
    private final TaskManager taskManager;
    private State state;

    public Game(Main pl) {
        this.pl = pl;
        this.state = State.LOBBY;
        this.playerManager = new PlayerManager(this);
        this.taskManager = new TaskManager(this);
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }

    public Main getPlugin() {
        return pl;
    }

    public String toTime(int seconds) {
        return String.format("%02d:%02d", seconds / 60, seconds % 60);
    }

    public State getState() {
        return state;
    }

    public void setState(State s) {
        this.state = s;
    }
}
