package gg.eris.lobby.listeners;

import gg.eris.commons.bukkit.util.ParticleUtil;
import gg.eris.lobby.ErisLobby;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class LaunchPadListener implements Listener {

  private static final double PLATE_LAUNCH_LATERAL_MAGNITUDE = 2;
  private static final double PLATE_LAUNCH_VERTICAL_BOOST = 1.25;

  private static final int PARTICLES_PER_POINT = 10;
  private static final int SPIRAL_POINT_COUNT = 120;
  private static final double SPIRAL_HEIGHT = 1.5;
  private static final double SPIRAL_RADIUS = 0.75;
  private static final int SPIRAL_ROTATIONS = 3;

  private final ErisLobby plugin;

  public LaunchPadListener(ErisLobby plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();

    if (event.getAction() == Action.PHYSICAL && event.getClickedBlock() != null
        && event.getClickedBlock().getType() == Material.GOLD_PLATE) {

      Vector velocity = player.getLocation().getDirection();

      velocity.setX(velocity.getX() * PLATE_LAUNCH_LATERAL_MAGNITUDE);
      velocity.setY(
          Math.max(PLATE_LAUNCH_VERTICAL_BOOST, velocity.getY() + PLATE_LAUNCH_VERTICAL_BOOST));
      velocity.setZ(velocity.getZ() * PLATE_LAUNCH_LATERAL_MAGNITUDE);

      Bukkit.getScheduler().runTaskLater(plugin, () -> {
        player.setVelocity(velocity);

        player.getWorld().playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 1, 1);

        Location origin = event.getClickedBlock().getLocation().add(new Vector(0.5, 0.5, 0.5));

        ParticleUtil.spawnSpiralForPlayer(EnumParticle.FLAME, false, PARTICLES_PER_POINT, origin,
            SPIRAL_POINT_COUNT, SPIRAL_HEIGHT, SPIRAL_RADIUS, SPIRAL_ROTATIONS, player);
      }, 1);
    }
  }
}
