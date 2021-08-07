package gg.eris.lobby.npcs.impl;

import gg.eris.commons.bukkit.text.TextController;
import gg.eris.commons.bukkit.text.TextType;
import gg.eris.lobby.npcs.ErisBaseLobbyNpc;
import org.bukkit.entity.Player;

public final class ErisUhcLobbyNpc extends ErisBaseLobbyNpc {

  @Override
  public String getIgn() {
    return "Eris UHC";
  }

  @Override
  public String getId() {
    return "uhc-npc";
  }

  @Override
  public String getSkinName() {
    return "Alfie";
  }

  @Override
  public void onClickedByPlayer(Player player) {
    player.performCommand("uhc");
  }

}
