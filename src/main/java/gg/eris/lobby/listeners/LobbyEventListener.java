package gg.eris.lobby.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public final class LobbyEventListener implements Listener {

  private static final int VOID_DAMAGE_THRESHOLD = -64;

  private final Location spawnLocation;

  public LobbyEventListener(Location spawnLocation) {
    this.spawnLocation = spawnLocation;
  }

  @EventHandler
  public void onEntityDamage(EntityDamageEvent event) {
    event.setCancelled(true);
  }

  @EventHandler
  public void onBlockDestroyed(BlockBreakEvent event) {
    event.setCancelled(true);
  }

  @EventHandler
  public void onPlayerMoved(PlayerMoveEvent event) {
    if (event.getPlayer().getLocation().getBlockY() < VOID_DAMAGE_THRESHOLD && spawnLocation != null) {
      event.getPlayer().teleport(spawnLocation);
    }
  }

  @EventHandler
  public void onSaturationLost(FoodLevelChangeEvent event) {
    event.setCancelled(true);
  }
}
