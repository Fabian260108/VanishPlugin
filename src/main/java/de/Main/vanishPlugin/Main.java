package de.Main.vanishPlugin;

import de.Main.vanishPlugin.Command.VanishCommand;
import de.Main.vanishPlugin.Listener.PlayerListener;
import de.Main.vanishPlugin.Manager.VanishManager;
import de.Main.vanishPlugin.Utils.RankUtil;
import net.luckperms.api.LuckPerms;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    private VanishManager vanishManager;

    @Override
    public void onEnable() {
        instance = this;

        LuckPerms luckPerms = getServer().getServicesManager().load(LuckPerms.class);
        RankUtil rankUtil = new RankUtil(luckPerms);
        vanishManager = new VanishManager(rankUtil);

        getServer().getPluginManager().registerEvents(new PlayerListener(vanishManager), this);

        this.getCommand("vanish").setExecutor(new VanishCommand(vanishManager));
    }

    public static Main getInstance() {
        return instance;
    }

    public VanishManager getVanishManager() {
        return vanishManager;
    }
}
