package de.Main.vanishPlugin.Listener;

import de.Main.vanishPlugin.Manager.VanishManager;
import de.Main.vanishPlugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    private final VanishManager vanishManager;

    public PlayerListener(VanishManager vanishManager) {
        this.vanishManager = vanishManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player joined = event.getPlayer();

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            vanishManager.updateVisibilityForAll(joined);
        }, 5L);
    }
}
