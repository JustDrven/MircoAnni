package dev.justdrven.microanni.menu.actions;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface MenuAction {

    void execute(Player player, ItemStack item);

}
