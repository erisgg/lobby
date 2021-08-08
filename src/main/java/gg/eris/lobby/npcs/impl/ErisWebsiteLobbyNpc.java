package gg.eris.lobby.npcs.impl;

import gg.eris.commons.bukkit.text.ClickEvent;
import gg.eris.commons.bukkit.text.HoverEvent;
import gg.eris.commons.bukkit.text.TextController;
import gg.eris.commons.bukkit.text.TextType;
import gg.eris.commons.bukkit.util.CC;
import gg.eris.lobby.npcs.ErisBaseLobbyNpc;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.entity.Player;

public class ErisWebsiteLobbyNpc extends ErisBaseLobbyNpc {

  private static final String WEBSITE_URL = "https://eris.gg";

  @Override
  public String getIgn() {
    return "Website";
  }

  @Override
  public String getId() {
    return "website-npc";
  }


  @Override
  public String getTextureData() {
    return "ewogICJ0aW1lc3RhbXAiIDogMTYyODQ1MTk4NzQ2NSwKICAicHJvZmlsZUlkIiA6ICJmMTA0NzMxZjljYTU0NmI0OTkzNjM4NTlkZWY5N2NjNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJ6aWFkODciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQ4ZmUxMzM5YzQ1YmExNzc0YjBiZGFhYjgxOGMxMjI5NjA2OGNiNWUwOWM0Mzk2MjRhZjE5NGY3ZmQ3ZDkzZCIKICAgIH0KICB9Cn0=";
  }

  @Override
  public String getTextureSignature() {
    return "W/Vu6N4IJiPYpIaMyKkl6PdTvCQIEaQWkTJ9yioLnTeqdl7yl4bXhiogMGfKKhLprSKM5Zh64MJhwC++MndaZfGlzzph3urscyEm0pzVV0CWJ5QcUNWsJDKKwaLpeUAsHP2SlPHC2nztA5RyUisjjyBOJHpNABgL4j8RlXKHwUA7nk0ZPbjdE4iShipgmx+JqjxlA06Z9YrBDyY2EAZxuihoGZGNJgbJhTWxxwsmvg+VvWQC3FcIjrkftZrfyy5GNomZRUrfAiROgSMeNKYIPhNgcclsp4H5uy6cv/fnvrHD8OVrJOkDbhwVWxB9rVZ9RUo7j/hJAWOR3AbP4QD9rDK8KZX5K4fb4wGDnMyN3umqQMaNC2JIQ8G8uveZuB4Jt7DkaCSZct7tObFr6arBAVtZAGgeN9azHE5smcFHO3Lx1/JnIEBZwKnQWr2DWlByrMpbcaJWQYoSPH6ncYVev7/S0HYnebAPO13kUy8mgx+VfJk7kFZvSSwGUfPhAfbFfYi23ivPSRH875e6/sNrScVKpSsX3oxO5+VpG/yzwquy1QrIleC6bj2PLgfw276GE3b1azheXUeL8hvOc0fBOYZvH2sKb2ru6KN4rnPwLNcKm8diA3VvdeT3tccchaKusVRhxK86w9veE3MQ33l8wr0sI83WAZxZGRmqv89iHQc=";
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
