package de.Main.vanishPlugin.Manager;

import de.Main.vanishPlugin.Main;
import de.Main.vanishPlugin.Utils.RankUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class VanishManager {

    private final Set<UUID> vanished = new HashSet<>();
    private final RankUtil rankUtil;

    public VanishManager(RankUtil rankUtil) {
        this.rankUtil = rankUtil;
    }

    public void setVanished(Player player, boolean vanish) {
        if (vanish) {
            vanished.add(player.getUniqueId());
        } else {
            vanished.remove(player.getUniqueId());
        }
        updateVisibilityForAll(player);
    }

    public boolean isVanished(Player player) {
        return vanished.contains(player.getUniqueId());
    }

    public void updateVisibilityForAll(Player changedPlayer) {
        for (Player other : Bukkit.getOnlinePlayers()) {
            updateVisibility(other, changedPlayer);
            updateVisibility(changedPlayer, other);
        }
    }

    public void updateVisibility(Player viewer, Player target) {
        if (isVanished(target)) {
            if (viewer.equals(target)) {
                viewer.showPlayer(Main.getInstance(), target);
                return;
            }

            String viewerRank = rankUtil.getHighestRank(viewer);
            String targetRank = rankUtil.getHighestRank(target);

            int viewerWeight = rankUtil.getRankWeight(viewerRank);
            int targetWeight = rankUtil.getRankWeight(targetRank);

            if (viewerWeight >= targetWeight) {
                viewer.showPlayer(Main.getInstance(), target);
            } else {
                viewer.hidePlayer(Main.getInstance(), target);
            }
        } else {
            viewer.showPlayer(Main.getInstance(), target);
        }
    }
}
