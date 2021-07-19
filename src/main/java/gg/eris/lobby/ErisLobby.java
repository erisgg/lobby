package gg.eris.lobby;

import gg.eris.commons.bukkit.ErisBukkitCommons;
import gg.eris.commons.bukkit.command.CommandManager;
import gg.eris.lobby.command.SpawnLocationCommand;
import gg.eris.lobby.listener.FlyPearlListener;
import gg.eris.lobby.listener.LaunchPadListener;
import gg.eris.lobby.listener.LobbyProtectionListener;
import gg.eris.lobby.listener.MobSpawnListener;
import gg.eris.lobby.listener.PlayerJoinListener;
import gg.eris.lobby.listener.ScoreboardListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
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
