package gg.eris.lobby.menu;

import gg.eris.commons.bukkit.menu.Menu;
import gg.eris.commons.bukkit.menu.MenuViewer;
import gg.eris.commons.core.identifier.Identifier;
import gg.eris.lobby.menu.item.GoldenApple;
import org.bukkit.plugin.java.JavaPlugin;

public class CompassMenu extends Menu {

  public CompassMenu(JavaPlugin owningPlugin) {
    super(owningPlugin, Identifier.of("compass", "game_selector"), 3);
    setFillItem(Menu.LIGHT_FILLER);
    addItem(13, new GoldenApple());
  }

  @Override
  public String getTitle(MenuViewer viewer) {
    return "Game Selector";
  }


}
