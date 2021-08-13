package gg.eris.lobby.command;

import gg.eris.commons.bukkit.command.Command.Builder;
import gg.eris.commons.bukkit.command.CommandManager;
import gg.eris.commons.bukkit.command.CommandProvider;
import gg.eris.commons.bukkit.text.TextController;
import gg.eris.commons.bukkit.text.TextType;
import gg.eris.commons.bukkit.util.PlayerUtil;
import gg.eris.lobby.ErisLobby;
import gg.eris.lobby.ErisLobbyIdentifiers;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UhcCommand implements CommandProvider {

  private final ErisLobby plugin;

  @Override
  public Builder getCommand(CommandManager manager) {
    return manager.newCommandBuilder(
        "uhc",
        "Sends you to the UHC.",
        "uhc",
        ErisLobbyIdentifiers.UHC_PERMISSION
    ).noArgsHandler(context -> {
      TextController.send(
          context.getCommandSender(),
          TextType.INFORMATION,
          "Connecting you to <h>UHC</h>..."
      );
      PlayerUtil.sendToServer(context.getSenderAsPlayer(), this.plugin.getUhcServerName());
    }, true);
  }
}
