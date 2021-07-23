package gg.eris.lobby.listener;

import gg.eris.commons.bukkit.text.ClickEvent;
import gg.eris.commons.bukkit.text.HoverEvent;
import gg.eris.commons.bukkit.text.TextController;
import gg.eris.commons.bukkit.text.TextType;
import gg.eris.lobby.ErisLobby;
import gg.eris.lobby.menu.CompassMenu;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

@RequiredArgsConstructor
public class ItemListener implements Listener {

  private static final Vector Y_ADDENDUM = new Vector(0, 0.5, 0);
  private static final double PEARL_LAUNCH_MAGNITUDE = 2;
  private static final String STORE_URL = "https://store.eris.gg";

  private final ErisLobby plugin;

  @EventHandler
  public void onRightClick(PlayerInteractEvent event) {
    if (!event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getAction()
        .equals(Action.RIGHT_CLICK_BLOCK)) {
      return;
    }

    Player player = event.getPlayer();
    ItemStack item = event.getPlayer().getItemInHand();

    if (item == null || item.getType() == Material.AIR) {
      return;
    }

    if (item.getType() == Material.COMPASS) {
      event.setCancelled(true);
      CompassMenu menu = new CompassMenu(ErisLobby.getPlugin(ErisLobby.class));
      menu.openMenu(player);
    } else if (item.getType() == Material.ENDER_PEARL) {
      event.setCancelled(true);
      Vector velocity = player.getEyeLocation().getDirection().multiply(PEARL_LAUNCH_MAGNITUDE)
          .add(Y_ADDENDUM);
      player.setVelocity(velocity);
      player.updateInventory();
    } else if (item.getType() == Material.CHEST) {
      event.setCancelled(true);
      TextController.send(
          player,
          TextType.INFORMATION,
          Int2ObjectMaps.singleton(0, ClickEvent.of(ClickEvent.Action.OPEN_URL, STORE_URL)),
          Int2ObjectMaps.singleton(0, HoverEvent.of("<col=yellow>Click to open our store</col>")),
          "Visit our store: <h><u><event=0>store.eris.gg</event></u></h>"
      );
    }

    syncInventory(player);
  }

  private void syncInventory(Player player) {
    Bukkit.getScheduler().runTaskLater(this.plugin, player::updateInventory, 1L);
  }
}
