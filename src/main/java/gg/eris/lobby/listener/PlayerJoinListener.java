package gg.eris.lobby.listener;

import gg.eris.commons.bukkit.util.CC;
import gg.eris.commons.bukkit.util.ItemBuilder;
import gg.eris.commons.bukkit.util.PlayerUtil;
import gg.eris.lobby.ErisLobby;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public final class PlayerJoinListener implements Listener {

  private final static ItemStack GAME_SELECTOR = new ItemBuilder(Material.COMPASS)
      .withName(CC.GREEN.bold() + "Game Selector" + CC.DARK_GRAY + " (Right Click)").build();

  private final static ItemStack FLY_PEARL = new ItemBuilder(Material.ENDER_PEARL, 16)
      .withName(CC.GOLD.bold() + "Fly Pearl" + CC.DARK_GRAY + " (Right Click)").build();

  private final ErisLobby plugin;

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    event.setJoinMessage(null);

    Player player = event.getPlayer();
    PlayerUtil.resetPlayer(player);
    player.getInventory().setItem(0, FLY_PEARL);
    player.getInventory().setItem(4, GAME_SELECTOR);

    this.plugin.getScoreboard().addPlayerToScoreboard(player);
  }
}
