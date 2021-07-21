package gg.eris.lobby.listener;

import gg.eris.lobby.ErisLobby;
import gg.eris.lobby.menu.CompassMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class RightClickCompassListener implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(event.getPlayer().getItemInHand().getType() == Material.COMPASS){
                CompassMenu menu = new CompassMenu(ErisLobby.getPlugin(ErisLobby.class));
                Player player = event.getPlayer();
                menu.openMenu(player);
            }
        }
    }
}
