package gg.eris.lobby.listener;

import gg.eris.commons.bukkit.util.CC;
import gg.eris.commons.bukkit.util.ItemBuilder;
import gg.eris.commons.bukkit.util.PlayerUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public final class PlayerJoinListener implements Listener {

  private final static ItemStack GAME_SELECTOR = new ItemBuilder(Material.COMPASS)
      .withName(CC.GREEN.bold() + "Game Selector" + CC.DARK_GRAY + " (Right Click)").build();

  private final static ItemStack FLY_PEARL = new ItemBuilder(Material.ENDER_PEARL)
      .withName(CC.GOLD.bold() + "Fly Pearl" + CC.DARK_GRAY + " (Right Click)").build();

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    PlayerUtil.resetPlayer(player);
    player.getInventory().setItem(0, FLY_PEARL);
    player.getInventory().setItem(4, GAME_SELECTOR);
  }
}
