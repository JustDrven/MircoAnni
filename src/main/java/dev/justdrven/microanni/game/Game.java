package dev.justdrven.microanni.game;

import dev.justdrven.microanni.Main;
import dev.justdrven.microanni.game.map.MapManager;
import dev.justdrven.microanni.game.player.PlayerManager;
import dev.justdrven.microanni.game.state.State;
import dev.justdrven.microanni.game.state.tasks.TaskManager;
import dev.justdrven.microanni.menu.MenuManager;

public class Game {

    private final Main pl;
    private final PlayerManager playerManager;
    private final TaskManager taskManager;
    private final MapManager mapManager;
    private final MenuManager menuManager;
    private State state;

    public Game(Main pl) {
        this.pl = pl;
        this.state = State.LOBBY;
        this.playerManager = new PlayerManager(this);
        this.taskManager = new TaskManager(this);
        this.mapManager = new MapManager(pl);
        this.menuManager = new MenuManager(pl);
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }

    public MapManager getMapManager() {
        return mapManager;
    }


    public MenuManager getMenuManager() {
        return menuManager;
    }

    public Main getPlugin() {
        return pl;
    }

    public String toTime(int seconds) {
        return String.format("%02d:%02d", seconds / 60, seconds % 60);
    }

    public void checkPlayersToEnd() {
        if (getState() != State.INGAME) return;

        if (this.getPlayerManager().getPlayers().size() <= 1) {
            // TODO: Task to end
        }
    }

    public void GameChecktoStart() {
        if (getState() != State.LOBBY) return;

        if (this.getPlayerManager().getPlayers().size() >= 1) {
            this.getTaskManager().getGameStartTask().runTaskTimer(pl, 0L, 20L);
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State s) {
        this.state = s;
    }
}
