package gg.eris.lobby.menu.item;

import gg.eris.commons.bukkit.menu.Menu;
import gg.eris.commons.bukkit.menu.MenuItem;
import gg.eris.commons.bukkit.menu.MenuViewer;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GoldenApple implements MenuItem {

    private static final ItemStack item = new ItemStack(Material.GOLDEN_APPLE);

    @Override
    public ItemStack getItem(MenuViewer menuViewer, Menu menu) {

        return item;
    }

    @Override
    public void onClick(MenuViewer menuViewer, InventoryClickEvent event) {

    }
}
