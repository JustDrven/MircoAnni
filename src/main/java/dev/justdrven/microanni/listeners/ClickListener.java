package dev.justdrven.microanni.listeners;

import dev.justdrven.microanni.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickListener implements Listener {

    private final Main pl;

    public ClickListener(Main pl) {
        this.pl = pl;
    }

    @EventHandler
    public void invclick(PlayerInteractEvent e) {
        Action a = e.getAction();
        Player p = e.getPlayer();
        if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
            if (p.getItemInHand().getType().equals(Material.WOOL)) {
                pl.getGame().getMenuManager().getTeam().open(p);
                e.setCancelled(true);
            }
        }
    }

}
