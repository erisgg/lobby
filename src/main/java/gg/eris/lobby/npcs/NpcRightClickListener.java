package gg.eris.lobby.npcs;

import gg.eris.lobby.ErisLobby;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NpcRightClickListener implements Listener {

  private ErisLobby plugin;

  public NpcRightClickListener(ErisLobby plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public final void onRightClicked(NPCRightClickEvent event) {
    for (ErisBaseLobbyNpc npc : plugin.getNpcs()) {
      if (npc.matches(event.getNPC())) {
        npc.onRightClickedByPlayer(event.getClicker());
      }
    }
  }
}
