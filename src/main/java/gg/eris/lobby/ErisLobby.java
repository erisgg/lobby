package gg.eris.lobby;

import gg.eris.commons.bukkit.ErisBukkitCommons;
import gg.eris.lobby.command.SpawnLocationCommand;
import gg.eris.lobby.listener.FlyPearlListener;
import gg.eris.lobby.listener.MobSpawnListener;
import gg.eris.lobby.listener.PlayerJoinListener;
import gg.eris.lobby.listener.LaunchPadListener;
import gg.eris.lobby.listener.LobbyProtectionListener;
import gg.eris.lobby.listener.ScoreboardListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ErisLobby extends JavaPlugin {

  @Override
  public void onEnable() {
    registerEvents();
    registerCommands();
  }

  private void registerEvents() {
    PluginManager manager = Bukkit.getServer().getPluginManager();

    manager.registerEvents(new LobbyProtectionListener((Location) getConfig().get("spawn-location")), this);
    manager.registerEvents(new FlyPearlListener(), this);
    manager.registerEvents(new LaunchPadListener(this), this);
    manager.registerEvents(new PlayerJoinListener(), this);
    manager.registerEvents(new ScoreboardListener(), this);
    manager.registerEvents(new MobSpawnListener(), this);
  }

  private void registerCommands() {
    ErisBukkitCommons commons = Bukkit.getServicesManager().getRegistration(ErisBukkitCommons.class)
        .getProvider();

    commons.getCommandManager().registerCommands(new SpawnLocationCommand(this));
  }
}
