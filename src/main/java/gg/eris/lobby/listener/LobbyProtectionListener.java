package gg.eris.lobby.listener;

import gg.eris.lobby.ErisLobby;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.PlayerInventory;

public final class LobbyProtectionListener implements Listener {

  private static final int VOID_DEPTH_THRESHOLD = -64;

  private Location spawnLocation;

  public LobbyProtectionListener(ErisLobby plugin) {
    FileConfiguration config = plugin.getConfig();

    if (config.isConfigurationSection("spawn-location")) {
      ConfigurationSection section = config.getConfigurationSection("spawn-location");
      this.spawnLocation = new Location(
          Bukkit.getWorld(section.getString("world")),
          section.getDouble("x"),
          section.getDouble("y"),
          section.getDouble("z"),
          (float) section.getDouble("yaw"),
          (float) section.getDouble("pitch")
      );
    }

    if (this.spawnLocation != null) {
      World world = spawnLocation.getWorld();
      Bukkit.getScheduler().runTaskTimer(plugin, () -> world.setTime(6000), 0L, 1L);
      world.setAutoSave(false);
      updateBorder();
    } else {
      Bukkit.getLogger().warning("Invalid world supplied: " + config.getString("world"));
      Bukkit.getServer().shutdown();
    }
  }

  @EventHandler
  public void onWeatherChange(WeatherChangeEvent event) {
    if (event.toWeatherState()) {
      event.setCancelled(true);
    }
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
    if (!(event.getEntity() instanceof LivingEntity) || event.getEntityType() == EntityType.PLAYER) {
      event.setCancelled(true);
    }
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

  @EventHandler
  public void onInventoryClick(InventoryClickEvent event) {
    if (event.getInventory() instanceof PlayerInventory) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onInventoryDrag(InventoryDragEvent event) {
    event.setCancelled(true);
  }

  public void setSpawnLocation(Location spawnLocation) {
    this.spawnLocation = spawnLocation;
    updateBorder();
  }

  public void updateBorder() {
    WorldBorder border = this.spawnLocation.getWorld().getWorldBorder();
    border.setSize(1000);
    border.setCenter(this.spawnLocation);
  }

}
