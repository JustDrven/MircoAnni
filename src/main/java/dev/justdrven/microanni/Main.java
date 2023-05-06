package dev.justdrven.microanni;

import dev.justdrven.microanni.commands.GameCommand;
import dev.justdrven.microanni.game.Game;
import dev.justdrven.microanni.listeners.ClickListener;
import dev.justdrven.microanni.listeners.PlayerListener;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private Game game;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        this.game = new Game(this);

        this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        this.getServer().getPluginManager().registerEvents(new ClickListener(this), this);
        this.getCommand("game").setExecutor(new GameCommand(this));
    }

    public Game getGame() {
        return game;
    }


}
