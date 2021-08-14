package gg.eris.lobby.scoreboard;

import gg.eris.commons.bukkit.scoreboard.CommonsScoreboard;
import gg.eris.commons.bukkit.util.CC;
import gg.eris.commons.core.identifier.Identifier;
import gg.eris.lobby.ErisLobby;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class ScoreboardListener implements Listener {

  private final CommonsScoreboard scoreboard;

  private volatile int networkCount;

  public ScoreboardListener(ErisLobby plugin) {
    this.scoreboard = plugin.getCommons().getScoreboardController().newScoreboard(
        Identifier.of("lobby", "scoreboard"));

    this.scoreboard.setTitle((player, tick) -> {
      tick = tick % 77;
      if (tick < 60) {
        return CC.GOLD.bold() + "『 " + CC.YELLOW.bold() + "Eris" + CC.GOLD.bold() + " 』";
      } else if (tick < 62) {
        return CC.WHITE.bold() + "『 "
            + CC.YELLOW.bold() + "Eris" + CC.GOLD.bold() + " 』";
      } else if (tick < 64) {
        return CC.WHITE.bold() + "『 E"
            + CC.YELLOW.bold() + "ris" + CC.GOLD.bold() + " 』";
      } else if (tick < 66) {
        return CC.WHITE.bold() + "『 Er"
            + CC.YELLOW.bold() + "is" + CC.GOLD.bold() + " 』";
      } else if (tick < 68) {
        return CC.GOLD.bold() + "『 "
            + CC.WHITE.bold() + "Eri"
            + CC.YELLOW.bold() + "s" + CC.GOLD.bold() + " 』";
      } else if (tick < 70) {
        return CC.GOLD.bold() + "『 " + CC.YELLOW.bold()
            + "E" + CC.WHITE.bold() + "ris"
            + CC.GOLD.bold() + " 』";
      } else if (tick < 72) {
        return CC.GOLD.bold() + "『 " + CC.YELLOW.bold()
            + "Er" + CC.WHITE.bold() + "is 』";
      } else if (tick < 74) {
        return CC.GOLD.bold() + "『 " + CC.YELLOW.bold()
            + "Eri" + CC.WHITE.bold() + "s 』";
      } else {
        return CC.GOLD.bold() + "『 " + CC.YELLOW.bold()
            + "Eris" + CC.WHITE.bold() + " 』";
      }
    });

    this.scoreboard.addLine("");
    this.scoreboard
        .addLine((p, t) -> CC.GREEN + "Rank: " + p.getPriorityRank().getColoredDisplay());
    this.scoreboard.addLine("");
    this.scoreboard
        .addLine((p, t) -> CC.GREEN + "Lobby: " + CC.WHITE + Bukkit.getOnlinePlayers().size(), 1);
    this.scoreboard
        .addLine((p, t) -> CC.GREEN + "Network: " + CC.WHITE + this.networkCount, 1);
    this.scoreboard.addLine("");
    this.scoreboard.addLine(CC.YELLOW + "Play @ eris.gg");

    Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () ->
        this.networkCount = plugin.getCommons().getNetworkPlayerCount(), 0L, 1L);
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    this.scoreboard.addPlayer(event.getPlayer());
  }


}
