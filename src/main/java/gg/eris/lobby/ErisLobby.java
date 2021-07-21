package gg.eris.lobby;

import gg.eris.commons.bukkit.ErisBukkitCommons;
import gg.eris.commons.bukkit.command.CommandManager;
import gg.eris.commons.bukkit.menu.Menu;
import gg.eris.commons.bukkit.menu.MenuItem;
import gg.eris.commons.bukkit.menu.item.ActionlessMenuItem;
import gg.eris.lobby.command.SpawnLocationCommand;
import gg.eris.lobby.listener.*;
import gg.eris.lobby.menu.CompassMenu;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ErisLobby extends JavaPlugin {

  @Override
  public void onEnable() {
    ErisBukkitCommons commons = Bukkit.getServicesManager().getRegistration(ErisBukkitCommons.class)
        .getProvider();

    saveDefaultConfig();
    FileConfiguration config = getConfig();

    PluginManager pluginManager = Bukkit.getServer().getPluginManager();
    CommandManager commandManager = commons.getCommandManager();

    pluginManager
        .registerEvents(new LobbyProtectionListener((Location) getConfig().get("spawn-location")),
            this);
    pluginManager.registerEvents(new FlyPearlListener(), this);
    pluginManager.registerEvents(new LaunchPadListener(this), this);
    pluginManager.registerEvents(new PlayerJoinListener(), this);
    pluginManager.registerEvents(new ScoreboardListener(), this);
    pluginManager.registerEvents(new MobSpawnListener(), this);
    pluginManager.registerEvents(new RightClickCompassListener(), this);
    commandManager.registerCommands(new SpawnLocationCommand(this));

    World world = Bukkit.getWorld(config.getString("world"));
    if (world != null) {
      Bukkit.getScheduler().runTaskTimer(this, () -> world.setTime(6000), 0L, 1L);
    } else {
      Bukkit.getLogger().warning("Invalid world supplied: " + config.getString("world"));
      Bukkit.getServer().shutdown();
    }

  }

}
