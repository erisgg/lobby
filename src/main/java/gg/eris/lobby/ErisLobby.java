package gg.eris.lobby;

import com.fasterxml.jackson.databind.ObjectMapper;
import gg.eris.commons.bukkit.ErisBukkitCommons;
import gg.eris.commons.bukkit.command.CommandManager;
import gg.eris.commons.bukkit.scoreboard.Scoreboard;
import gg.eris.commons.bukkit.scoreboard.ScoreboardController;
import gg.eris.commons.bukkit.util.CC;
import gg.eris.commons.core.identifier.Identifier;
import gg.eris.lobby.command.SpawnLocationCommand;
import gg.eris.lobby.listener.ItemListener;
import gg.eris.lobby.listener.LaunchPadListener;
import gg.eris.lobby.listener.LobbyProtectionListener;
import gg.eris.lobby.listener.MobSpawnListener;
import gg.eris.lobby.listener.PlayerJoinListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ErisLobby extends JavaPlugin {

  @Getter
  private Scoreboard scoreboard;

  @Override
  public void onEnable() {
    ErisBukkitCommons commons = Bukkit.getServicesManager().getRegistration(ErisBukkitCommons.class)
        .getProvider();

    saveDefaultConfig();

    PluginManager pluginManager = Bukkit.getServer().getPluginManager();
    CommandManager commandManager = commons.getCommandManager();
    ScoreboardController scoreboardController = commons.getScoreboardController();

    LobbyProtectionListener lobbyProtectionListener;

    pluginManager
        .registerEvents(lobbyProtectionListener = new LobbyProtectionListener(this),
            this);
    pluginManager.registerEvents(new LaunchPadListener(this), this);
    pluginManager.registerEvents(new PlayerJoinListener(this), this);
    pluginManager.registerEvents(new MobSpawnListener(), this);
    pluginManager.registerEvents(new ItemListener(this), this);
    commandManager.registerCommands(new SpawnLocationCommand(this, lobbyProtectionListener));

    this.scoreboard = scoreboardController.createScoreboard(
        Identifier.of("lobby", "scoreboard"),
        CC.GOLD.bold() + "『 " + CC.YELLOW.bold() + "Eris" + CC.GOLD.bold() + " 』"
    );

    this.scoreboard.addLine("");

    this.scoreboard
        .addLine(() -> CC.GOLD + "Rank: " + CC.RED.bold() + "Owner");

    this.scoreboard.addLine("");

    this.scoreboard
        .addLine(() -> CC.GOLD + "Lobby: " + CC.YELLOW + Bukkit.getOnlinePlayers().size());
    this.scoreboard
        .addLine(() -> CC.GOLD + "Network: " + CC.YELLOW + Bukkit.getOnlinePlayers().size());

    this.scoreboard.addLine("");
    this.scoreboard.addLine(CC.GRAY.italic() + "      eris.gg");
  }

}
