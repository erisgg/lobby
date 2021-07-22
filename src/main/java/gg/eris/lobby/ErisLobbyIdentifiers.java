package gg.eris.lobby;

import gg.eris.commons.bukkit.ErisBukkitCommons;
import gg.eris.commons.bukkit.permission.Permission;
import gg.eris.commons.bukkit.permission.PermissionRegistry;
import gg.eris.commons.core.identifier.Identifier;
import org.bukkit.Bukkit;

public class ErisLobbyIdentifiers {

  public static final Identifier SPAWN_LOCATION_PERMISSION = permission("spawnlocation");

  public static final Identifier NPC_PLACEMENT_PERMISSION = permission("placenpc");

  private static Identifier permission(String name) {
    PermissionRegistry registry =
        Bukkit.getServicesManager()
            .getRegistration(ErisBukkitCommons.class)
            .getProvider()
            .getPermissionRegistry();

    return Permission.ofDefault(registry, name).getIdentifier();
  }

}
