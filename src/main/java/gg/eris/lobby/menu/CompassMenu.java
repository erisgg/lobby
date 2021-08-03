package gg.eris.lobby.menu;

import gg.eris.commons.bukkit.menu.Menu;
import gg.eris.commons.bukkit.menu.MenuItem;
import gg.eris.commons.bukkit.menu.MenuViewer;
import gg.eris.commons.core.identifier.Identifier;
import gg.eris.lobby.menu.item.GoldenApple;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class CompassMenu extends Menu {
    private static final String title = "Game Selector";

    public CompassMenu(JavaPlugin owningPlugin) {
        super(owningPlugin, Identifier.of("menu", "game selector"), 3);
        setFillItem(new ItemStack(Material.STAINED_GLASS_PANE));
        MenuItem goldenApple = new GoldenApple();
        addItem(13, goldenApple);
    }

    @Override
    public String getTitle(MenuViewer viewer) {
        return title;
    }



}
