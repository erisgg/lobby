package gg.eris.lobby;

import gg.eris.commons.bukkit.ErisBukkitCommons;
import gg.eris.commons.bukkit.command.CommandManager;
import gg.eris.commons.bukkit.rank.Rank;
import gg.eris.commons.bukkit.rank.RankRegistry;
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
import java.util.Map;
import java.util.Set;
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
import org.spigotmc.SpigotConfig;

public final class ErisLobby extends JavaPlugin {

  private static final String LIVE_UHC_SET = "custom_craft_uhc_games";
  private static final int SERVER_COUNT = 5;

  private static final Map<String, String> NAME_TO_PORT = Map.of(
      "uhc-0", "25510",
      "uhc-1", "25511",
      "uhc-2", "25512",
      "uhc-3", "25513",
      "uhc-4", "25514"
  );

  private static final String WHITELIST_MESSAGE
      = CC.GOLD.bold() + "(!) " + CC.GOLD + "Eris is in maintenance mode.";

  @Getter
  private ErisBukkitCommons commons;

  @Getter
  private List<ErisBaseLobbyNpc> npcs;

  @Getter
  private volatile String uhcServerName;

  @Override
  public void onEnable() {
    this.commons = Bukkit.getServicesManager().getRegistration(ErisBukkitCommons.class)
        .getProvider();

    saveDefaultConfig();

    PluginManager pluginManager = Bukkit.getServer().getPluginManager();
    CommandManager commandManager = this.commons.getCommandManager();
    TablistController tablistController = this.commons.getTablistController();

    LobbyProtectionListener lobbyProtectionListener;

    pluginManager
        .registerEvents(lobbyProtectionListener = new LobbyProtectionListener(this),
            this);
    pluginManager.registerEvents(new LaunchPadListener(this), this);
    pluginManager.registerEvents(new PlayerJoinListener(), this);
    pluginManager.registerEvents(new MobSpawnListener(), this);
    pluginManager.registerEvents(new ItemListener(this), this);
    pluginManager.registerEvents(new ScoreboardListener(this), this);
    pluginManager.registerEvents(new NpcRightClickListener(this), this);

    commandManager.registerCommands(
        new SpawnLocationCommand(this, lobbyProtectionListener),
        new PlaceNPCCommand(this),
        new UhcCommand(this)
    );

    tablistController.setHeader(CC.YELLOW + "You are playing on " + CC.GOLD.bold() + "ERIS.GG");
    tablistController.setFooter(CC.GOLD + "Visit our store at " + CC.YELLOW.bold() +
        "STORE.ERIS.GG");
    tablistController.setDisplayNameFunction((player, viewer) -> {
      Rank rank = player.getNicknameProfile().getPriorityDisplayRank();
      return rank == RankRegistry.get().DEFAULT ?
          CC.GRAY + player.getDisplayName() : rank.getColor().getColor() +
          "[" + rank.getRawDisplay() + "] " + CC.WHITE + player.getDisplayName();
    });

    Bukkit.getScheduler().runTaskLater(this, () -> {
      registerNPCs();
      spawnSavedNPCs();
      SpigotConfig.whitelistMessage = WHITELIST_MESSAGE;
    }, 20L);

    Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
      if (this.uhcServerName == null) {
        getNewServer();
      } else {
        Set<String> active = this.commons.getRedisWrapper().querySet(LIVE_UHC_SET);
        if (active.contains(NAME_TO_PORT.get(this.uhcServerName))) {
          getNewServer();
        }
      }
    }, 0, 2);
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
        new ErisUhcLobbyNpc(this),
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

  private void getNewServer() {
    Set<String> active = this.commons.getRedisWrapper().querySet(LIVE_UHC_SET);
    for (int i = 0; i < SERVER_COUNT; i++) {
      String name = "uhc-" + i;
      if (!active.contains(NAME_TO_PORT.get(name))) {
        this.uhcServerName = name;
        return;
      }
    }
  }


}
