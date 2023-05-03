package dev.justdrven.microanni;

import dev.justdrven.microanni.game.Game;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private Game game;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        this.game = new Game(this);
    }

    public Game getGame() {
        return game;
    }
}
