package gg.eris.lobby.command;

import gg.eris.commons.bukkit.command.Command.Builder;
import gg.eris.commons.bukkit.command.CommandManager;
import gg.eris.commons.bukkit.command.CommandProvider;
import gg.eris.commons.bukkit.command.argument.StringArgument;
import gg.eris.commons.bukkit.text.TextController;
import gg.eris.commons.bukkit.text.TextType;
import gg.eris.lobby.ErisLobby;
import gg.eris.lobby.ErisLobbyIdentifiers;
import gg.eris.lobby.npcs.ErisLobbyNPC;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class PlaceNPCCommand implements CommandProvider {

  private final ErisLobby plugin;

  @Override
  public Builder getCommand(CommandManager manager) {
    return manager.newCommandBuilder(
        "placenpc",
        "Places the NPC with the associated string ID at the sender's location.",
        ErisLobbyIdentifiers.NPC_PLACEMENT_PERMISSION
    ).noArgsHandler(context -> {
      TextController.send(
          context.getSenderAsPlayer(),
          TextType.ERROR,
          "Invalid command usage!"
      );
    }, true).withSubCommand().argument(StringArgument.of("npcName")).handler(context -> {
      Player player = context.getSenderAsPlayer();
      String name = context.getArgument("npcName");

      String configEntryName = String.format("%s-location", name);

      plugin.getConfig().set(configEntryName, player.getLocation());
      plugin.saveConfig();

      ErisLobbyNPC npc = plugin.getNpcs().stream()
          .filter(erisLobbyNPC -> erisLobbyNPC.getId().equals(name)).findFirst().get();

      String message;

      if (npc.isSpawned()) {
        npc.teleport(player.getLocation());

        message = String.format("Config entry for %s updated!", name);
      }
      else {
        npc.spawn(player.getLocation());

        message = String.format("Config entry for %s created!", name);
      }

      TextController.send(
          player,
          TextType.SUCCESS,
          message
      );
    }).asPlayerOnly().finished();
  }
}
