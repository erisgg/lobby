package gg.eris.lobby.listener;

import gg.eris.commons.bukkit.text.TextController;
import gg.eris.commons.bukkit.text.TextType;
import gg.eris.commons.bukkit.util.CC;
import gg.eris.lobby.ErisLobby;
import gg.eris.lobby.menu.CompassMenu;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.chat.ComponentSerializer;
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

  private final ErisLobby plugin;

  @EventHandler
  public void onRightClick(PlayerInteractEvent event) {
    if (!event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getAction()
        .equals(Action.RIGHT_CLICK_BLOCK)) {
      return;
    }

    Player player = event.getPlayer();
    ItemStack item = event.getPlayer().getItemInHand();

    if (item != null && item.getType() != Material.AIR) {
      syncInventory(player);
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
      player.spigot().sendMessage(ComponentSerializer.parse("{\"text\":\"This is a test\"}"));
      TextController.send(TextController.builder(
          "Visit our store: <event=0>store.eris.gg</event>",
          TextType.INFORMATION
          ).withHoverEvent(
          0,
          new HoverEvent(
              HoverEvent.Action.SHOW_TEXT,
              new ComponentBuilder(CC.YELLOW + "Click to open our store").create())
          ).withClickEvent(0, new ClickEvent(ClickEvent.Action.OPEN_URL, "https://store.eris.gg"))
          , player);
    }


  }

  private void syncInventory(Player player) {
    Bukkit.getScheduler().runTaskLater(this.plugin, player::updateInventory, 1L);
  }
}
