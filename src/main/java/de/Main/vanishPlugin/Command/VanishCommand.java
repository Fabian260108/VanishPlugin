package de.Main.vanishPlugin.Command;

import de.Main.vanishPlugin.Manager.VanishManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    private final VanishManager vanishManager;

    public VanishCommand(VanishManager vanishManager) {
        this.vanishManager = vanishManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Nur Spieler können diesen Befehl nutzen!");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("vanish.use")) {
            player.sendMessage("§cDu hast keine Rechte, diesen Befehl zu nutzen!");
            return true;
        }

        boolean currentlyVanished = vanishManager.isVanished(player);

        vanishManager.setVanished(player, !currentlyVanished);

        if (!currentlyVanished) {
            player.sendMessage("§aDu bist nun §6unsichtbar§a!");
        } else {
            player.sendMessage("§aDu bist nun §6sichtbar§a!");
        }

        return true;
    }
}
