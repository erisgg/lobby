package gg.eris.lobby.npcs;

import gg.eris.lobby.ErisLobby;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class NpcRightClickListener implements Listener {

  private ErisLobby plugin;

  public NpcRightClickListener(ErisLobby plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onRightClicked(NPCRightClickEvent event) {
    for (ErisBaseLobbyNpc npc : plugin.getNpcs()) {
      if (npc.matches(event.getNPC())) {
        npc.onClickedByPlayer(event.getClicker());
      }
    }
  }

  @EventHandler
  public void onLeftClicked(NPCLeftClickEvent event) {
    for (ErisBaseLobbyNpc npc : plugin.getNpcs()) {
      if (npc.matches(event.getNPC())) {
        npc.onClickedByPlayer(event.getClicker());
      }
    }
  }
}
