package gg.eris.lobby.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class FlyPearlEventListener implements Listener {

  private static final double PEARL_LAUNCH_MAGNITUDE = 1.7;

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    if ((event.getAction() == Action.RIGHT_CLICK_AIR
        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
        && event.getPlayer().getItemInHand().getType() == Material.ENDER_PEARL) {

      event.setCancelled(true);

      Location location = event.getPlayer().getEyeLocation();

      Vector velocityBoost = location.getDirection().multiply(PEARL_LAUNCH_MAGNITUDE);

      event.getPlayer().setVelocity(event.getPlayer().getVelocity().add(velocityBoost));

      event.getPlayer().updateInventory();
    }
  }
}
