package gg.eris.lobby.npcs;

import gg.eris.lobby.ErisLobby;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NpcLeftClickListener implements Listener {

  private ErisLobby plugin;

  public NpcLeftClickListener(ErisLobby plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public final void onLeftClicked(NPCLeftClickEvent event) {
    for (ErisBaseLobbyNpc npc : plugin.getNpcs()) {
      if (npc.matches(event.getNPC())) {
        npc.onClickedByPlayer(event.getClicker());
      }
    }
  }
}