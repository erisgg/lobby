package gg.eris.lobby.listener;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import gg.eris.commons.bukkit.util.ParticleUtil;
import gg.eris.lobby.ErisLobby;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public final class LaunchPadListener implements Listener {

  private static final double PLATE_LAUNCH_LATERAL_MAGNITUDE = 3.25;
  private static final double PLATE_LAUNCH_VERTICAL_BOOST = 1;

  private static final int PARTICLES_PER_POINT = 10;
  private static final int SPIRAL_POINT_COUNT = 120;
  private static final double SPIRAL_HEIGHT = 1.5;
  private static final double SPIRAL_RADIUS = 0.75;
  private static final int SPIRAL_ROTATIONS = 3;

  private final ErisLobby plugin;
  private final Cache<UUID, Boolean> cooldowns;

  public LaunchPadListener(ErisLobby plugin) {
    this.plugin = plugin;
    this.cooldowns = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS).build();
  }

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    if (event.getAction() == Action.PHYSICAL && event.getClickedBlock() != null
        && event.getClickedBlock().getType() == Material.GOLD_PLATE) {

      event.setCancelled(true);
      event.setUseInteractedBlock(Result.DENY);
      event.setUseItemInHand(Result.DENY);

      Boolean cooldown = this.cooldowns.getIfPresent(player.getUniqueId());
      if (cooldown != null) {
        return;
      }

      this.cooldowns.put(player.getUniqueId(), true);

      Vector velocity = player.getLocation().getDirection();
      velocity.setX(velocity.getX() * PLATE_LAUNCH_LATERAL_MAGNITUDE);
      velocity.setY(
          Math.max(PLATE_LAUNCH_VERTICAL_BOOST, velocity.getY() + PLATE_LAUNCH_VERTICAL_BOOST));
      velocity.setZ(velocity.getZ() * PLATE_LAUNCH_LATERAL_MAGNITUDE);

      Bukkit.getScheduler().runTaskLater(plugin, () -> {
        player.setVelocity(velocity);
        player.playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 1, 1);

        // Particles
        //Location origin = event.getClickedBlock().getLocation().add(new Vector(0.5, 0.5, 0.5));
        //ParticleUtil.spawnSpiralForPlayer(EnumParticle.FLAME, false, PARTICLES_PER_POINT, origin,
           // SPIRAL_POINT_COUNT, SPIRAL_HEIGHT, SPIRAL_RADIUS, SPIRAL_ROTATIONS, player);
      }, 1);
    }
  }
}
