package gg.eris.lobby.listeners;

import gg.eris.commons.bukkit.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class GiveItemsListener implements Listener {

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();

    player.getInventory().clear();

    player.getInventory().setItem(8,
        new ItemBuilder(Material.ENDER_PEARL).withName("&6&lFly Pearl &8(Right Click to Fly!)")
            .build());
  }
}
