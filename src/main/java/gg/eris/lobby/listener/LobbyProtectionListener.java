package gg.eris.lobby.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public final class LobbyProtectionListener implements Listener {

  private static final int VOID_DEPTH_THRESHOLD = -64;

  private final Location spawnLocation;

  public LobbyProtectionListener(Location spawnLocation) {
    this.spawnLocation = spawnLocation;
  }

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
      event.setCancelled(true);
      event.setUseInteractedBlock(Result.DENY);
      event.setUseItemInHand(Result.DENY);
    }
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
  public void onPlayerMove(PlayerMoveEvent event) {
    if (event.getPlayer().getLocation().getBlockY() < VOID_DEPTH_THRESHOLD
        && this.spawnLocation != null) {
      event.getPlayer().teleport(this.spawnLocation);
    }
  }

  @EventHandler
  public void onSaturationLost(FoodLevelChangeEvent event) {
    if (event.getFoodLevel() < ((Player) event.getEntity()).getFoodLevel()) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onItemDropped(PlayerDropItemEvent event) {
    event.setCancelled(true);
  }

  @EventHandler
  public void onItemPickedUp(PlayerPickupItemEvent event) {
    event.setCancelled(true);
  }
}
