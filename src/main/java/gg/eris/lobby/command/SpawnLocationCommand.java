package gg.eris.lobby.command;

import gg.eris.commons.bukkit.command.Command.Builder;
import gg.eris.commons.bukkit.command.CommandManager;
import gg.eris.commons.bukkit.command.CommandProvider;
import gg.eris.commons.bukkit.text.TextController;
import gg.eris.commons.bukkit.text.TextType;
import gg.eris.lobby.ErisLobby;
import gg.eris.lobby.ErisLobbyIdentifiers;
import gg.eris.lobby.listener.LobbyProtectionListener;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public final class SpawnLocationCommand implements CommandProvider {

  private final ErisLobby plugin;
  private final LobbyProtectionListener lobbyProtectionListener;

  @Override
  public Builder getCommand(CommandManager manager) {
    return manager.newCommandBuilder(
        "spawnlocation",
        "spawnlocation",
        "Sets the spawn location for the given world name at the sender's position.",
        ErisLobbyIdentifiers.SPAWN_LOCATION_PERMISSION
    ).noArgsHandler(context -> {
      Player player = context.getSenderAsPlayer();
      Location spawnLocation = player.getLocation();

      this.plugin.getConfig().set("spawn-location.world", spawnLocation.getWorld().getName());
      this.plugin.getConfig().set("spawn-location.x", spawnLocation.getX());
      this.plugin.getConfig().set("spawn-location.y", spawnLocation.getY());
      this.plugin.getConfig().set("spawn-location.z", spawnLocation.getZ());
      this.plugin.getConfig().set("spawn-location.yaw", spawnLocation.getYaw());
      this.plugin.getConfig().set("spawn-location.pitch", spawnLocation.getPitch());
      this.plugin.saveConfig();

      lobbyProtectionListener.setSpawnLocation(spawnLocation);

      TextController.send(
          player,
          TextType.SUCCESS,
          "Spawn location updated"
      );
    }, true);

  }
}
