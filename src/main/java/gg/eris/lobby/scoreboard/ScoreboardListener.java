package gg.eris.lobby.scoreboard;

import gg.eris.commons.bukkit.scoreboard.CommonsScoreboard;
import gg.eris.commons.bukkit.scoreboard.ScoreboardController;
import gg.eris.commons.bukkit.util.CC;
import gg.eris.commons.core.identifier.Identifier;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ScoreboardListener implements Listener {

  private final CommonsScoreboard scoreboard;

  public ScoreboardListener(ScoreboardController scoreboardController) {
    this.scoreboard = scoreboardController.newScoreboard(Identifier.of("lobby", "scoreboard"));

    this.scoreboard.setTitle((player, tick) -> {
      if (tick < 30) {
        return CC.GOLD.bold() + "Eris";
      } else if (tick < 60) {
        return CC.YELLOW.bold() + "Eris";
      } else if (tick < 90) {
        return CC.WHITE.bold() + "Eris";
      } else {
        return CC.GRAY.bold() + "Eris";
      }
    });

    this.scoreboard.addLine("");
    this.scoreboard.addLine((p, t) -> CC.GREEN + "Rank: " + p.getRank().getColoredDisplay());
    this.scoreboard.addLine("");
    this.scoreboard.addLine((p, t) -> CC.GREEN + "Lobby: " + CC.WHITE + Bukkit.getOnlinePlayers().size(), 20);
    this.scoreboard.addLine((p, t) -> CC.GREEN + "Network: " + CC.WHITE + Bukkit.getOnlinePlayers().size(), 20);
    this.scoreboard.addLine("");
    this.scoreboard.addLine(CC.YELLOW + "Play @ eris.gg");
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    this.scoreboard.addPlayer(event.getPlayer());
  }


}
