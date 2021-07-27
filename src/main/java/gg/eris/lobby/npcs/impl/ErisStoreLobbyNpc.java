package gg.eris.lobby.npcs.impl;

import gg.eris.commons.bukkit.text.ClickEvent;
import gg.eris.commons.bukkit.text.HoverEvent;
import gg.eris.commons.bukkit.text.TextController;
import gg.eris.commons.bukkit.text.TextType;
import gg.eris.lobby.npcs.ErisBaseLobbyNpc;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import org.bukkit.entity.Player;

public final class ErisStoreLobbyNpc extends ErisBaseLobbyNpc {

  private static final String STORE_URL = "https://store.eris.gg";

  @Override
  public String getIgn() {
    return "Visit our store!";
  }

  @Override
  public String getId() {
    return "store-npc";
  }

  @Override
  public String getSkinName() {
    return "Alfie";
  }

  @Override
  public void onRightClickedByPlayer(Player player) {
    TextController.send(
        player,
        TextType.INFORMATION,
        Int2ObjectMaps.singleton(0, ClickEvent.of(ClickEvent.Action.OPEN_URL, STORE_URL)),
        Int2ObjectMaps.singleton(0, HoverEvent.of("<col=yellow>Click to open our store</col>")),
        "Visit our store: <h><u><event=0>store.eris.gg</event></u></h>"
    );
  }
}
