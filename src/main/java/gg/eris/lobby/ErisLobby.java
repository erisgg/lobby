package gg.eris.lobby;

import com.google.common.collect.Lists;
import gg.eris.commons.bukkit.ErisBukkitCommons;
import gg.eris.commons.bukkit.command.CommandManager;
import gg.eris.commons.bukkit.scoreboard.ScoreboardController;
import gg.eris.commons.bukkit.tablist.TablistController;
import gg.eris.commons.bukkit.util.CC;
import gg.eris.lobby.command.PlaceNPCCommand;
import gg.eris.lobby.command.SpawnLocationCommand;
import gg.eris.lobby.command.UhcCommand;
import gg.eris.lobby.listener.ItemListener;
import gg.eris.lobby.listener.LaunchPadListener;
import gg.eris.lobby.listener.LobbyProtectionListener;
import gg.eris.lobby.listener.MobSpawnListener;
import gg.eris.lobby.listener.PlayerJoinListener;
import gg.eris.lobby.npcs.ErisBaseLobbyNpc;
import gg.eris.lobby.npcs.NpcRightClickListener;
import gg.eris.lobby.npcs.impl.ErisComingSoonLobbyNpc;
import gg.eris.lobby.npcs.impl.ErisStoreLobbyNpc;
import gg.eris.lobby.npcs.impl.ErisUhcLobbyNpc;
import gg.eris.lobby.npcs.impl.ErisWebsiteLobbyNpc;
import gg.eris.lobby.scoreboard.ScoreboardListener;
import java.util.List;
import lombok.Getter;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ErisLobby extends JavaPlugin {

  @Getter
  private List<ErisBaseLobbyNpc> npcs;

  @Override
  public void onEnable() {
    ErisBukkitCommons commons = Bukkit.getServicesManager().getRegistration(ErisBukkitCommons.class)
        .getProvider();

    saveDefaultConfig();

    PluginManager pluginManager = Bukkit.getServer().getPluginManager();
    CommandManager commandManager = commons.getCommandManager();
    ScoreboardController scoreboardController = commons.getScoreboardController();
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
    pluginManager.registerEvents(new NpcRightClickListener(this), this);

    commandManager.registerCommands(
        new SpawnLocationCommand(this, lobbyProtectionListener),
        new PlaceNPCCommand(this),
        new UhcCommand()
    );

    tablistController.setHeader(CC.YELLOW + "You are playing on " + CC.GOLD.bold() + "ERIS.GG");
    tablistController.setFooter(CC.GOLD + "Visit our store at " + CC.YELLOW.bold() +
        "STORE.ERIS.GG");
    tablistController.setDisplayNameFunction((player, viewer) ->
        (player.getPriorityRank() == commons.getRankRegistry().DEFAULT ?
            CC.GRAY + player.getName() :
                player.getPriorityRank().getColor().getColor() + "[" + player.getPriorityRank()
                    .getRawDisplay() + "] " + CC.WHITE + player.getName()));

    Bukkit.getScheduler().runTaskLater(this, () -> {
      registerNPCs();
      spawnSavedNPCs();
    }, 20L);
  }

  @Override
  public void onDisable() {
    for (ErisBaseLobbyNpc npc : this.npcs) {
      npc.despawn();
    }
  }

  private void registerNPCs() {
    for (NPCRegistry registry : CitizensAPI.getNPCRegistries()) {
      registry.despawnNPCs(DespawnReason.REMOVAL);
      registry.sorted().forEach(NPC::destroy);
      registry.deregisterAll();
    }

    this.npcs = List.of(
        new ErisComingSoonLobbyNpc(),
        new ErisStoreLobbyNpc(),
        new ErisUhcLobbyNpc(),
        new ErisWebsiteLobbyNpc()
    );
  }

  private void spawnSavedNPCs() {
    FileConfiguration config = this.getConfig();

    for (ErisBaseLobbyNpc npc : this.npcs) {
      String configEntryName = npc.getId() + "-location";

      if (config.contains(configEntryName)) {
        Location location = (Location) config.get(configEntryName);
        npc.spawn(location);
      }
    }
  }
}
