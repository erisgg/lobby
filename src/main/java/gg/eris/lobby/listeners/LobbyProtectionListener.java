package gg.eris.lobby.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public final class LobbyProtectionListener implements Listener {

  private static final int VOID_DEPTH_THRESHOLD = -64;

  private final Location spawnLocation;

  public LobbyProtectionListener(Location spawnLocation) {
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
    if (event.getPlayer().getLocation().getBlockY() < VOID_DEPTH_THRESHOLD
        && spawnLocation != null) {
      event.getPlayer().teleport(spawnLocation);
    }
  }

  @EventHandler
  public void onSaturationLost(FoodLevelChangeEvent event) {
    event.setCancelled(true);
  }

  @EventHandler
  public void onItemDropped(PlayerDropItemEvent event) { event.setCancelled(true); }

  @EventHandler
  public void onItemPickedUp(PlayerPickupItemEvent event) { event.setCancelled(true); }
}
