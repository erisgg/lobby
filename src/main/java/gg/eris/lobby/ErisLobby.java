package gg.eris.lobby;

import gg.eris.commons.bukkit.ErisBukkitCommons;
import gg.eris.lobby.commands.SpawnLocationCommand;
import gg.eris.lobby.listeners.FlyPearlEventListener;
import gg.eris.lobby.listeners.LaunchPadListener;
import gg.eris.lobby.listeners.LobbyEventListener;
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

    manager.registerEvents(new LobbyEventListener((Location) getConfig().get("spawn-location")), this);
    manager.registerEvents(new FlyPearlEventListener(), this);
    manager.registerEvents(new LaunchPadListener(this), this);
  }

  private void registerCommands() {
    ErisBukkitCommons commons = Bukkit.getServicesManager().getRegistration(ErisBukkitCommons.class)
        .getProvider();

    commons.getCommandManager().registerCommands(new SpawnLocationCommand(this));
  }
}
