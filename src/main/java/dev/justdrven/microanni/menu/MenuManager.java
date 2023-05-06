package dev.justdrven.microanni.menu;

import dev.justdrven.microanni.Main;
import dev.justdrven.microanni.utils.Colors;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class MenuManager {

    private final Main pl;
    private final Menu team;
    private final HashMap<Player, String> redTeam;
    private final HashMap<Player, String> blueTeam;

    public MenuManager(Main pl) {
        this.pl = pl;

        this.blueTeam = new HashMap<>();
        this.redTeam = new HashMap<>();
        this.team = new Menu(Colors.format("&8&lTeam Selector"), 9, pl);
        this.registerActions();
        this.registerItems();
    }

    public Menu getTeam() {
        return team;
    }

    private void registerActions() {
        // Action Team Selector
        this.getTeam().setAction((player, item) -> {
            if (item.getType().equals(Material.WOOL) &&
                    item.getItemMeta().getDisplayName().equals(Colors.format("&9&lBlue"))) {
                if (redTeam.containsKey(player)) {
                    redTeam.remove(player);
                }
                if (!blueTeam.containsKey(player)) {
                    blueTeam.put(player, player.getName());
                    player.sendMessage(Colors.format("&8[&bServer&8] &fSelected team: Blue"));
                } else {
                    player.sendMessage(Colors.format("&8[&bServer&8] &fYou already selected this team!"));
                }
            }
            if (item.getType().equals(Material.WOOL) &&
                    item.getItemMeta().getDisplayName().equals(Colors.format("&c&lRed"))) {
                if (blueTeam.containsKey(player)) {
                    blueTeam.remove(player);
                }
                if (!redTeam.containsKey(player)) {
                    redTeam.put(player, player.getName());
                    player.sendMessage(Colors.format("&8[&bServer&8] &fSelected team: Red"));
                } else {
                    player.sendMessage(Colors.format("&8[&bServer&8] &fYou already selected this team!"));
                }

            }
        });
    }

    private void registerItems() {
        // Items Team Selector
        this.getTeam().setItem(0, Material.WOOL, 1, 11, Colors.format("&9&lBlue"), Arrays.asList(Colors.format(
                "&3Click to select"
        ),
                Colors.format("&8-")));
        this.getTeam().setItem(1, Material.WOOL, 1, 14, Colors.format("&c&lRed"), Collections.singletonList(Colors.format(
                "&3Click to select"
        )));
    }

}
