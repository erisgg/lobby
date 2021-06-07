package gg.eris.lobby.listeners;

import gg.eris.commons.bukkit.util.CC;
import gg.eris.commons.bukkit.util.SidebarBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;

public final class ScoreboardListener implements Listener {

  private final Map<UUID, Scoreboard> scoreboards;

  public ScoreboardListener() {
    scoreboards = new HashMap<>();
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {

    Scoreboard newScoreboard = new SidebarBuilder(CC.GOLD.bold() + "- ERIS.GG -")
        .withLine(CC.AQUA + event.getPlayer().getName())
        .build();

    event.getPlayer().setScoreboard(newScoreboard);
    scoreboards.put(event.getPlayer().getUniqueId(), newScoreboard);
  }

  @EventHandler
  public void onPlayerLeave(PlayerQuitEvent event) {
    scoreboards.remove(event.getPlayer().getUniqueId());
  }
}
