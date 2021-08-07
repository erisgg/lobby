package gg.eris.lobby.menu.item;

import gg.eris.commons.bukkit.menu.Menu;
import gg.eris.commons.bukkit.menu.MenuItem;
import gg.eris.commons.bukkit.menu.MenuViewer;
import gg.eris.commons.bukkit.util.CC;
import gg.eris.commons.bukkit.util.ItemBuilder;
import gg.eris.commons.bukkit.util.PlayerUtil;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class UhcMenuItem implements MenuItem {

  private static final ItemStack ITEM = new ItemBuilder(Material.GOLDEN_APPLE)
      .withName(CC.GREEN.bold() + "Eris UHC")
      .withLore(CC.GREEN.italic() + "Click to join Eris UHC")
      .build();

  @Override
  public ItemStack getItem(MenuViewer menuViewer, Menu menu) {
    return ITEM;
  }

  @Override
  public void onClick(MenuViewer viewer, InventoryClickEvent event) {
    PlayerUtil.sendToServer(viewer.getPlayer(), "uhc");
  }
}
