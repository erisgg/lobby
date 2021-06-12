package gg.eris.lobby.commands;

import gg.eris.commons.bukkit.command.Command.Builder;
import gg.eris.commons.bukkit.command.CommandManager;
import gg.eris.commons.bukkit.command.CommandProvider;
import gg.eris.commons.core.identifier.Identifier;
import gg.eris.lobby.ErisLobby;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;

@RequiredArgsConstructor
public class SpawnLocationCommand implements CommandProvider {

  private final ErisLobby plugin;

  @Override
  public Builder getCommand(CommandManager manager) {
    return manager.newCommandBuilder("sl",
        "Sets the spawn location for the given world name at the sender's position", Identifier.of("eris", "admin"))
        .noArgsHandler(context -> {
          Location spawnLocation = context.getSenderAsPlayer().getLocation();

          plugin.getConfig().set("spawn-location", spawnLocation);
          plugin.saveConfig();

          context.getSenderAsPlayer().sendMessage(String.format("Set lobby spawn location to %s!", spawnLocation));
        });

  }
}
