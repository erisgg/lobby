package gg.eris.lobby.scoreboard;

import com.google.common.collect.Maps;
import gg.eris.commons.bukkit.player.ErisPlayer;
import gg.eris.commons.bukkit.player.ErisPlayerManager;
import gg.eris.commons.bukkit.scoreboard.Scoreboard;
import gg.eris.commons.bukkit.scoreboard.ScoreboardController;
import gg.eris.commons.bukkit.util.CC;
import gg.eris.commons.core.identifier.Identifier;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ScoreboardListener implements Listener {

  private final ScoreboardController scoreboardController;
  private final ErisPlayerManager erisPlayerManager;

  private final Map<UUID, Scoreboard> scoreboards;

  public ScoreboardListener(ScoreboardController scoreboardController,
      ErisPlayerManager erisPlayerManager) {
    this.scoreboardController = scoreboardController;
    this.erisPlayerManager = erisPlayerManager;
    this.scoreboards = Maps.newHashMap();
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    ErisPlayer player = this.erisPlayerManager.getPlayer(event.getPlayer());

    Scoreboard scoreboard = scoreboardController.createScoreboard(
        Identifier.of("lobby_scoreboard", event.getPlayer().getUniqueId().toString()),
        CC.GOLD.bold() + "『 " + CC.YELLOW.bold() + "Eris" + CC.GOLD.bold() + " 』"
    );

    scoreboard.addLine("");

    scoreboard
        .addLine(() -> CC.GREEN + "Rank: " + player.getRank().getColoredDisplay());

    scoreboard.addLine("");

    scoreboard
        .addLine(() -> CC.GREEN + "Lobby: " + CC.WHITE + Bukkit.getOnlinePlayers().size());
    scoreboard
        .addLine(() -> CC.GREEN + "Network: " + CC.WHITE + Bukkit.getOnlinePlayers().size());

    scoreboard.addLine("");
    scoreboard.addLine(CC.YELLOW + "Play @ eris.gg");

    scoreboard.addPlayerToScoreboard(player.getHandle());
    this.scoreboards.put(player.getUniqueId(), scoreboard);
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    ErisPlayer player = this.erisPlayerManager.getPlayer(event.getPlayer());
    Scoreboard scoreboard = this.scoreboards.get(player.getUniqueId());
    this.scoreboardController.removeScoreboard(scoreboard.getIdentifier());
  }

}
