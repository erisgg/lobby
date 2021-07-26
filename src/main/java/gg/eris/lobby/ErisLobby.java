package gg.eris.lobby;

import gg.eris.commons.bukkit.ErisBukkitCommons;
import gg.eris.commons.bukkit.command.CommandManager;
import gg.eris.commons.bukkit.player.ErisPlayerManager;
import gg.eris.commons.bukkit.scoreboard.ScoreboardController;
import gg.eris.commons.bukkit.tablist.TablistController;
import gg.eris.commons.bukkit.util.CC;
import gg.eris.lobby.command.PlaceNPCCommand;
import gg.eris.lobby.command.SpawnLocationCommand;
import gg.eris.lobby.listener.ItemListener;
import gg.eris.lobby.listener.LaunchPadListener;
import gg.eris.lobby.listener.LobbyProtectionListener;
import gg.eris.lobby.listener.MobSpawnListener;
import gg.eris.lobby.listener.PlayerJoinListener;
import gg.eris.lobby.npcs.ErisLobbyNPC;
import gg.eris.lobby.scoreboard.ScoreboardListener;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ErisLobby extends JavaPlugin {

  @Getter
  private List<ErisLobbyNPC> npcs;

  @Override
  public void onEnable() {
    ErisBukkitCommons commons = Bukkit.getServicesManager().getRegistration(ErisBukkitCommons.class)
        .getProvider();

    saveDefaultConfig();

    PluginManager pluginManager = Bukkit.getServer().getPluginManager();
    CommandManager commandManager = commons.getCommandManager();
    ScoreboardController scoreboardController = commons.getScoreboardController();
    ErisPlayerManager erisPlayerManager = commons.getErisPlayerManager();
    TablistController tablistController = commons.getTablistController();

    LobbyProtectionListener lobbyProtectionListener;

    pluginManager
        .registerEvents(lobbyProtectionListener = new LobbyProtectionListener(this),
            this);
    pluginManager.registerEvents(new LaunchPadListener(this), this);
    pluginManager.registerEvents(new PlayerJoinListener(), this);
    pluginManager.registerEvents(new MobSpawnListener(), this);
    pluginManager.registerEvents(new ItemListener(this), this);
    pluginManager.registerEvents(new ScoreboardListener(scoreboardController), this);

    commandManager.registerCommands(new SpawnLocationCommand(this, lobbyProtectionListener),
        new PlaceNPCCommand(this));

    tablistController.setDisplayNameFunction(player -> player.getRank().getColor().getColor() + "["
        + player.getRank().getRawDisplay() + "] "
        + (player.getRank().isWhiteChat() ? CC.WHITE : CC.GRAY)
        + player.getName());

    registerNPCs();
    spawnSavedNPCs();
  }

  @Override
  public void onDisable() {
    for (ErisLobbyNPC npc : npcs) {
      npc.despawn();
    }
  }

  private void registerNPCs() {
    npcs = new ArrayList<>();

    // Add NPC instances here - e.g. npcs.add(new TrollNPC(this));
  }

  private void spawnSavedNPCs() {
    FileConfiguration config = this.getConfig();

    for (ErisLobbyNPC npc : npcs) {
      String configEntryName = String.format("%s-location", npc.getId());

      if (config.contains(configEntryName)) {
        Location location = (Location) config.get(configEntryName);

        npc.spawn(location);
      }
    }
  }
}
