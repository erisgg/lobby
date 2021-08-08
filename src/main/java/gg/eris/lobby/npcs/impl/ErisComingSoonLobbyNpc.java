package gg.eris.lobby.npcs.impl;

import gg.eris.commons.bukkit.text.TextController;
import gg.eris.commons.bukkit.text.TextType;
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
  public String getTextureData() {
    return
        "eyJ0aW1lc3RhbXAiOjE1ODU5NDE1NjEzMzksInByb2ZpbGVJZCI6IjRkNzA0ODZmNTA5MjRkMzM4NmJiZmM5YzEyYmFiNGFlIiwicHJvZmlsZU5hbWUiOiJzaXJGYWJpb3pzY2hlIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hZWI2ODFjODc1MGM2NGNhNGQ4MzQxNjNmYzhkNTMyYmZlMzIyZGRlOTI1Y2VmNjc0ODMzNzQ3ZGU5YzJjNTA4IiwibWV0YWRhdGEiOnsibW9kZWwiOiJzbGltIn19fX0=";
  }

  @Override
  public String getTextureSignature() {
    return "pNWxoXh27jYNqgKf2OlvTxJo3LEX2+6QMrCzGtD3uC8WEm70h/F"
        + "/cb01BS5jTOmykYUEnH0EKyhpolu984JrET7kN5fl+CyNd6gFQW8G9HQIx7JKCBCirEb0I9zEezC4Ct4XHt0y69WbPU8N6jtllwfFVer2dcgnXiT0uc2kMSgRfhh+Ok9VmWkdUEM86Bhm4Y8jkZEJp3AD/rgnjVhkavN6cz/KyMe4ovwSS+TwTs8b1c4iK9bxIBWvuR7FUgNC69axS7NktaIb4orPSPKZEeKD7eEzHBgZ5fZJuOZohBuA0K/b7/WDJOu10Wi3pWqAbIQAK6DhgUUtaKZ2Da+OSX3oPUqkctiexupCTk514droCNN+pZV73h0M9MXPjofaKYl9Wo7gB9xZHn7U+zJ+VQvJGzeer4lQvx0ThdWHlS1gR67JhpFj47bmha4Qx2r1wQQbV8K2cGzMFqvur1K3iJQUf2t4HL0Y3hhFCxjO7b9lAMEApz3QZXNqIrsJ8tYV7TQLAzpHhFmcsQFbQ78rjxv1DbiL7Pl0HlDWNIih4CdPQD22NYr1mO5M4qV6JkqYF4z98+aBiPSs88pgwYbwyTu97fXaqo1eLbTxolznxkk24bykR6m/xWOgPD7a9+xIU+4xB5UkJue0SAIBYEjT4by3OI+p9ELjPB0OuwXWKWE=";
  }

  @Override
  public void onClickedByPlayer(Player player) {
    TextController.send(
        player,
        TextType.PROMPT,
        "Coming soon..."
    );
  }
}
