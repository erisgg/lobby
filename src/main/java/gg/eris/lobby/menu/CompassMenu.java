package gg.eris.lobby.menu;

import gg.eris.commons.bukkit.menu.Menu;
import gg.eris.commons.bukkit.menu.MenuViewer;
import gg.eris.commons.core.identifier.Identifier;
import gg.eris.lobby.ErisLobby;
import gg.eris.lobby.menu.item.UhcMenuItem;
import org.bukkit.plugin.java.JavaPlugin;

public final class CompassMenu extends Menu {

  public CompassMenu(ErisLobby plugin) {
    super(plugin, Identifier.of("compass", "game_selector"), 3);
    setFillItem(Menu.LIGHT_FILLER);
    addItem(13, new UhcMenuItem(plugin));
  }

  @Override
  public String getTitle(MenuViewer viewer) {
    return "Game Selector";
  }


}
