package gg.eris.lobby;

import gg.eris.commons.bukkit.ErisBukkitCommons;
import gg.eris.commons.bukkit.permission.Permission;
import gg.eris.commons.bukkit.permission.PermissionRegistry;
import gg.eris.commons.core.identifier.Identifier;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Utility;

@UtilityClass
public class ErisLobbyIdentifiers {

  public static final Identifier SPAWN_LOCATION_PERMISSION = permission("spawnlocation");
  public static final Identifier NPC_PLACEMENT_PERMISSION = permission("placenpc");
  public static final Identifier UHC_PERMISSION = permission("uhc");

  private static Identifier permission(String name) {
    PermissionRegistry registry =
        Bukkit.getServicesManager()
            .getRegistration(ErisBukkitCommons.class)
            .getProvider()
            .getPermissionRegistry();

    return Permission.ofDefault(registry, name).getIdentifier();
  }

}
