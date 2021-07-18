package gg.eris.lobby.command;

import gg.eris.commons.bukkit.command.Command.Builder;
import gg.eris.commons.bukkit.command.CommandManager;
import gg.eris.commons.bukkit.command.CommandProvider;
import gg.eris.commons.core.identifier.Identifier;
import gg.eris.lobby.ErisLobby;
import gg.eris.lobby.ErisLobbyIdentifiers;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;

@RequiredArgsConstructor
public class SpawnLocationCommand implements CommandProvider {

  private final ErisLobby plugin;

  @Override
  public Builder getCommand(CommandManager manager) {
    return manager.newCommandBuilder(
        "spawnlocation",
        "Sets the spawn location for the given world name at the sender's position",
        ErisLobbyIdentifiers.SPAWN_LOCATION_PERMISSION
    ).noArgsHandler(context -> {
      Location spawnLocation = context.getSenderAsPlayer().getLocation();

      plugin.getConfig().set("spawn-location", spawnLocation);
      plugin.saveConfig();

      context.getSenderAsPlayer()
          .sendMessage(String.format("Set lobby spawn location to %s!", spawnLocation));
    });

  }
}
