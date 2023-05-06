package dev.justdrven.microanni.menu;

import dev.justdrven.microanni.Main;
import dev.justdrven.microanni.menu.actions.MenuAction;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Menu implements Listener {

    private final String title;
    private final int size;
    private final Inventory inventory;
    private final Main pl;
    private MenuAction action;

    public Menu(String title, int size, Main pl) {
        this.title = title;
        this.size = size;
        this.pl = pl;
        this.inventory = Bukkit.createInventory(null, size, title);

        this.pl.getServer().getPluginManager().registerEvents(this, pl);
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Main getPlugin() {
        return pl;
    }

    public void setItem(int slot, Material m, int amount, int data, String name, List<String> lores) {
        ItemStack is = new ItemStack(m, amount, (byte)data);
        ItemMeta ismeta = is.getItemMeta();
        ismeta.setDisplayName(name);
        ismeta.setLore(lores);
        is.setItemMeta(ismeta);

        this.getInventory().setItem(slot, is);
    }

    public MenuAction getAction() {
        return action;
    }

    public void open(final Player player) {
        player.openInventory(this.getInventory());
    }

    public void setAction(MenuAction action) {
        this.action = action;
    }

    @EventHandler
    public void click(InventoryClickEvent e) {
        if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', this.getTitle()))) {
            final Player player = (Player) e.getWhoClicked();
            final ItemStack item = e.getCurrentItem();

            e.setCancelled(true);
            this.getAction().execute(player, item);
        }
    }

}
