package gg.eris.lobby.command;

import gg.eris.commons.bukkit.command.Command.Builder;
import gg.eris.commons.bukkit.command.CommandManager;
import gg.eris.commons.bukkit.command.CommandProvider;
import gg.eris.commons.bukkit.text.TextController;
import gg.eris.commons.bukkit.text.TextType;
import gg.eris.lobby.ErisLobby;
import gg.eris.lobby.ErisLobbyIdentifiers;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

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
      Player player = context.getSenderAsPlayer();
      Location spawnLocation = player.getLocation();

      this.plugin.getConfig().set("spawn-location", spawnLocation);
      this.plugin.saveConfig();

      TextController.send(TextController.builder(
          "Spawn location updated",
          TextType.SUCCESS
      ), player);
    }, true);

  }
}
