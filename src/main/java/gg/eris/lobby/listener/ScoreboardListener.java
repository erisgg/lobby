package gg.eris.lobby.listener;

import gg.eris.commons.bukkit.util.CC;
import gg.eris.commons.bukkit.util.SidebarBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;

public final class ScoreboardListener implements Listener {

  private final Map<UUID, Scoreboard> scoreboards;

  public ScoreboardListener() {
    this.scoreboards = new HashMap<>();
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Scoreboard newScoreboard = new SidebarBuilder(CC.GOLD.bold() + "- ERIS.GG -")
        .withEmptyLine()
        .withLine(
            CC.GOLD + "Players online: " + CC.WHITE + Bukkit.getServer().getOnlinePlayers().size())
        .withEmptyLine()
        .build();

    event.getPlayer().setScoreboard(newScoreboard);
    this.scoreboards.put(event.getPlayer().getUniqueId(), newScoreboard);
  }

  @EventHandler
  public void onPlayerLeave(PlayerQuitEvent event) {
    this.scoreboards.remove(event.getPlayer().getUniqueId());
  }
}
