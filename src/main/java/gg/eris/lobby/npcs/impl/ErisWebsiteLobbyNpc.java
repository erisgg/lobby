package gg.eris.lobby.npcs.impl;

import gg.eris.commons.bukkit.text.ClickEvent;
import gg.eris.commons.bukkit.text.HoverEvent;
import gg.eris.commons.bukkit.text.TextController;
import gg.eris.commons.bukkit.text.TextType;
import gg.eris.lobby.npcs.ErisBaseLobbyNpc;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import org.bukkit.entity.Player;

public class ErisWebsiteLobbyNpc extends ErisBaseLobbyNpc {

  private static final String WEBSITE_URL = "https://eris.gg";

  @Override
  public String getIgn() {
    return "Visit our website!";
  }

  @Override
  public String getId() {
    return "website-npc";
  }

  @Override
  public String getSkinName() {
    return "Alfie";
  }

  @Override
  public void onClickedByPlayer(Player player) {
    TextController.send(
        player,
        TextType.INFORMATION,
        Int2ObjectMaps.singleton(0, ClickEvent.of(ClickEvent.Action.OPEN_URL, WEBSITE_URL)),
        Int2ObjectMaps.singleton(0, HoverEvent.of("<col=yellow>Click to open our website</col>")),
        "Visit our website: <h><u><event=0>eris.gg</event></u></h>"
    );
  }
}
