package gg.eris.lobby.command;

import gg.eris.commons.bukkit.command.Command.Builder;
import gg.eris.commons.bukkit.command.CommandManager;
import gg.eris.commons.bukkit.command.CommandProvider;
import gg.eris.commons.bukkit.command.argument.StringArgument;
import gg.eris.commons.bukkit.text.TextController;
import gg.eris.commons.bukkit.text.TextType;
import gg.eris.lobby.ErisLobby;
import gg.eris.lobby.ErisLobbyIdentifiers;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class SetDirectingCommand implements CommandProvider {

  private final ErisLobby plugin;

  @Override
  public Builder getCommand(CommandManager manager) {
    return manager.newCommandBuilder(
        "setdirecting",
        "sets directing.",
        "setdirecting [server]",
        ErisLobbyIdentifiers.SET_DIRECTING_PERMISSION
    ).withSubCommand()
        .argument(StringArgument.of("name"))
        .handler(context -> {
          String name = context.getArgument("name");
          this.plugin.setUhcServerName(name);
          TextController.send(
              context.getSenderAsPlayer(),
              TextType.INFORMATION,
              "Set directing to <h>{0}</h>",
              name
          );
        }).finished();
  }
}