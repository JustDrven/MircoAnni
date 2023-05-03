package dev.justdrven.microanni.game.player;

import dev.justdrven.microanni.game.Game;
import dev.justdrven.microanni.game.state.State;
import dev.justdrven.microanni.utils.Colors;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class PlayerManager {

    private Set<GamePlayer> players = new HashSet<>();
    private final Game game;

    public PlayerManager(Game game) { this.game = game; }

    public void add(UUID uuid) {
        this.players.add(new GamePlayer(uuid));
    }

    public void remove(UUID uuid) {
        this.players.removeIf(gamePlayer -> gamePlayer.getUUID().equals(uuid));
    }

    public Optional<GamePlayer> getPlayer(UUID uuid) {
        return this.getPlayers().stream()
                .filter(name -> name.getUUID().equals(uuid))
                .findAny();
    }


    public void clear(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
    }

    public void update() {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("Game", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        if (game.getState() == State.LOBBY) {
            obj.setDisplayName(Colors.format("&r &3&lMicroAnni&r "));
            obj.getScore(Colors.format("&r&l  &c ")).setScore(2);
            obj.getScore(Colors.format("&fMap: &bVoting..")).setScore(2);
            obj.getScore(Colors.format("&fPlayers: &b" + game.getPlayerManager().getPlayers().size())).setScore(2);
            obj.getScore(Colors.format("&e&l  &e&l  ")).setScore(2);
            obj.getScore(Colors.format("&7 &nmc.valonity.xyz&r  ")).setScore(1);
        }
        if (game.getState() == State.INGAME) {
            obj.setDisplayName(Colors.format("&r &3&lMicroAnni &f| &b" + game.toTime(game.getTaskManager().getGameStartTask().time)));
            obj.getScore(Colors.format("&r&l  &c ")).setScore(5);
            obj.getScore(Colors.format("&fMap: &b" + game.getPlugin().getConfig().getString("map-name"))).setScore(4);
            obj.getScore(Colors.format("&fPlayers: &b" + game.getPlayerManager().getPlayers().size())).setScore(3);
            obj.getScore(Colors.format("&e&l  &e&l  ")).setScore(2);
            obj.getScore(Colors.format("&7 &nmc.valonity.xyz&r  ")).setScore(1);
        }
        if (game.getState() == State.INGAME) {
            obj.setDisplayName(Colors.format("&r &3&lMicroAnni&r "));
            obj.getScore(Colors.format("&r&l  &c ")).setScore(4);
            obj.getScore(Colors.format("&fEnding...")).setScore(3);
            obj.getScore(Colors.format("&e&l  &e&l  ")).setScore(2);
            obj.getScore(Colors.format("&7 &nmc.valonity.xyz&r  ")).setScore(1);
        }
    }

    public Set<GamePlayer> getPlayers() {
        return players;
    }
}
