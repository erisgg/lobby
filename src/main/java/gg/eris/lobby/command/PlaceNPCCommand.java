package gg.eris.lobby.command;

import gg.eris.commons.bukkit.command.Command.Builder;
import gg.eris.commons.bukkit.command.CommandManager;
import gg.eris.commons.bukkit.command.CommandProvider;
import gg.eris.commons.bukkit.command.argument.StringArgument;
import gg.eris.commons.bukkit.text.TextController;
import gg.eris.commons.bukkit.text.TextType;
import gg.eris.lobby.ErisLobby;
import gg.eris.lobby.ErisLobbyIdentifiers;
import gg.eris.lobby.npcs.ErisBaseLobbyNpc;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public final class PlaceNPCCommand implements CommandProvider {

  private final ErisLobby plugin;

  @Override
  public Builder getCommand(CommandManager manager) {
    return manager.newCommandBuilder(
        "placenpc",
        "placenpc <name>",
        "Places the NPC with the associated string ID at the sender's location.",
        ErisLobbyIdentifiers.NPC_PLACEMENT_PERMISSION
    ).withSubCommand()
        .argument(StringArgument.of("npcName")).handler(context -> {
          Player player = context.getSenderAsPlayer();
          String name = context.getArgument("npcName");

          Optional<ErisBaseLobbyNpc> optionalNpc = this.plugin.getNpcs().stream()
              .filter(erisLobbyNPC -> erisLobbyNPC.getId().equals(name)).findFirst();

          if (optionalNpc.isEmpty()) {
            TextController.send(
                player,
                TextType.ERROR,
                "There is no NPC with the ID: " + name
            );

            return;
          }

          String configEntryName = name + "-location";

          this.plugin.getConfig().set(configEntryName, player.getLocation());
          this.plugin.saveConfig();

          ErisBaseLobbyNpc npc = optionalNpc.get();

          String message;

          if (npc.isSpawned()) {
            npc.teleport(player.getLocation());

            message = "Config entry for <h>{0}</h> updated!";
          } else {
            npc.spawn(player.getLocation());

            message = "Config entry for <h>{0}</h> created!";
          }

          TextController.send(
              player,
              TextType.SUCCESS,
              message,
              name
          );
        }).asPlayerOnly().finished();
  }
}
