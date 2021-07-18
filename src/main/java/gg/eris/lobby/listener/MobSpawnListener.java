package gg.eris.lobby.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MobSpawnListener implements Listener {

  @EventHandler
  public void onEntitySpawn(EntitySpawnEvent event) {
    event.setCancelled(true);
  }

}
