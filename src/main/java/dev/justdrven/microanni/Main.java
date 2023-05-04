package dev.justdrven.microanni;

import dev.justdrven.microanni.commands.GameCommand;
import dev.justdrven.microanni.game.Game;
import dev.justdrven.microanni.utils.ConfigFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private Game game;
    private ConfigFile location;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        this.location = new ConfigFile(this, "locations");
        this.game = new Game(this);

        this.getCommand("game").setExecutor(new GameCommand(this));
    }

    public Game getGame() {
        return game;
    }

    public ConfigFile getLocation() {
        return location;
    }

}
