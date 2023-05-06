package dev.justdrven.microanni.menu;

import dev.justdrven.microanni.Main;
import dev.justdrven.microanni.utils.Colors;

import org.bukkit.Material;

public class MenuManager {

    private final Main pl;
    private final Menu team;

    public MenuManager(Main pl) {
        this.pl = pl;

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
            if (item.getType().equals(Material.BED)) {
                player.closeInventory();
            }
        });
    }

    private void registerItems() {
        // Items Team Selector
        this.getTeam().setItem(8, Material.BED, 1, 0, Colors.format("&cClose"), null);
    }

}
