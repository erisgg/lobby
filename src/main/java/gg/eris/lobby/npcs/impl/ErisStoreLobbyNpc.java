package gg.eris.lobby.npcs.impl;

import gg.eris.commons.bukkit.text.ClickEvent;
import gg.eris.commons.bukkit.text.HoverEvent;
import gg.eris.commons.bukkit.text.TextController;
import gg.eris.commons.bukkit.text.TextType;
import gg.eris.commons.bukkit.util.CC;
import gg.eris.lobby.npcs.ErisBaseLobbyNpc;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import org.bukkit.entity.Player;

public final class ErisStoreLobbyNpc extends ErisBaseLobbyNpc {

  private static final String STORE_URL = "https://store.eris.gg";

  @Override
  public String getIgn() {
    return "Store";
  }

  @Override
  public String getId() {
    return "store-npc";
  }

  @Override
  public String getTextureData() {
    return "ewogICJ0aW1lc3RhbXAiIDogMTYyNjQ0OTcyNTI4MywKICAicHJvZmlsZUlkIiA6ICI5ZDQyNWFiOGFmZjg0MGU1OWM3NzUzZjc5Mjg5YjMyZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJUb21wa2luNDIiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWM2MWI5MTEyMWEyNWZkNDBmMzY3OGJlZGZmNDczNDA3MzBiNTU3N2M3MTk2ZTFiZGNhNGFkOWQwYjdjN2RkYyIKICAgIH0KICB9Cn0=";
  }

  @Override
  public String getTextureSignature() {
    return "pcW+sH0JJUpjR02/l9HJ/pCpnUeDQh5U673s/9xcHSNTdCDmAuaQNSS9KcipkqDsxcze0ED6ApFAZ2nrnXa8eEjyJ42Q+/oPgmUhNnRh5CdioSW5+1+CvdJxO1MyPaQqvaly2mEDv0FBve0jcxmdaX6872hFyGCxorPIC5za705besM6b4CSGROVC7xDKvN+nfFp2ckdY78qG337Z2s99fHKyWnOetXTMMxc40lH/m+/3wCFOGqL8UQLlOYz9tIOdHBJ/yRKeXDvaqKf0xpQ1r7y03JZ3AfwqC+ZSdwnMGaUVeg3I5/q/AEV73NubB527995F03RcTPPBgkUS7dODf/1ST0TIWnC5zceWFBgxcMqds2aW7UWay1jGOqyqgPhCVaNtvcFg4RIqN2s3Yv869uaFk3+QjYWLylMTXGhyPIdc5vgZh3CiGg9y3R5pbD0BvJv3P2dmSLIAXNZjpNlK7EMPPYcy3IxlLCBOuIMiMewgCfpzU7Kk3ghrOm347DXXi1bJ8jn+bZ9Rdgh/JG0UDWjtbjdti//ZZx1sbRGyGXiM36CB3hfyJ3Sh5CdyeFLEPTr7wJye+hzUe5S/iaBVkgy5HqUMYBdDijywgi3ZdPX5qIXyp85NYVk7M48nhEHkTNVq+0tJ+SOlbapA3HG8tbTddFWAc6kLZxsBK2aYMc=";
  }

  @Override
  public void onClickedByPlayer(Player player) {
    TextController.send(
        player,
        TextType.INFORMATION,
        Int2ObjectMaps.singleton(0, ClickEvent.of(ClickEvent.Action.OPEN_URL, STORE_URL)),
        Int2ObjectMaps.singleton(0, HoverEvent.of("<col=yellow>Click to open our store</col>")),
        "Visit our store: <h><u><event=0>store.eris.gg</event></u></h>"
    );
  }
}
