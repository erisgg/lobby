package gg.eris.lobby.npcs.impl;

import gg.eris.lobby.npcs.ErisBaseLobbyNpc;
import org.bukkit.entity.Player;

public class ErisComingSoonLobbyNpc extends ErisBaseLobbyNpc {

  @Override
  public String getIgn() {
    return "???";
  }

  @Override
  public String getId() {
    return "mystery-npc";
  }

  @Override
  public String getSkinName() {
    return "Alfie";
  }

  @Override
  public void onRightClickedByPlayer(Player player) {
    // Don't need to do anything here.
  }
}
