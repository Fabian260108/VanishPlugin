package de.Main.vanishPlugin.Utils;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;

public class RankUtil {

    private final LuckPerms luckPerms;

    public RankUtil(LuckPerms luckPerms) {
        this.luckPerms = luckPerms;
    }

    public String getHighestRank(Player player) {
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user == null) return "default";

        String primaryGroup = user.getPrimaryGroup();
        if (primaryGroup.equalsIgnoreCase("admin")) return "admin";
        if (primaryGroup.equalsIgnoreCase("teamleitung")) return "teamleitung";
        if (primaryGroup.equalsIgnoreCase("mod")) return "mod";
        if (primaryGroup.equalsIgnoreCase("supporter")) return "supporter";
        return "default";
    }

    public int getRankWeight(String rank) {
        switch (rank.toLowerCase()) {
            case "admin":
                return 4;
            case "teamleitung":
                return 3;
            case "mod":
                return 2;
            case "supporter":
                return 1;
            case "default":
                return 0;
            default:
                return 0;
        }
    }
}
