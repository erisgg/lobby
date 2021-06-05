package gg.eris.lobby.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class FlyPearlEventListener implements Listener {

  private static final double PEARL_LAUNCH_MAGNITUDE = 1.7;

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();

    if ((event.getAction() == Action.RIGHT_CLICK_AIR
        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
        && player.getItemInHand().getType() == Material.ENDER_PEARL) {

      event.setCancelled(true);

      Vector velocity = player.getEyeLocation().getDirection()
          .multiply(PEARL_LAUNCH_MAGNITUDE);

      player.setVelocity(velocity);
      player.updateInventory();
    }
  }
}
