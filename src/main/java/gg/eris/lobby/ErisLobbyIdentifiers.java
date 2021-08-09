package gg.eris.lobby;

import gg.eris.commons.bukkit.permission.Permission;
import gg.eris.commons.bukkit.permission.PermissionGroup;
import gg.eris.commons.bukkit.permission.PermissionRegistry;
import gg.eris.commons.core.identifier.Identifier;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ErisLobbyIdentifiers {

  public static final Identifier SPAWN_LOCATION_PERMISSION = permission("spawnlocation",
      PermissionGroup.HIGHER_STAFF);
  public static final Identifier NPC_PLACEMENT_PERMISSION = permission("placenpc",
      PermissionGroup.HIGHER_STAFF);
  public static final Identifier UHC_PERMISSION = permission("uhc", PermissionGroup.ALL);

  private static Identifier permission(String name, PermissionGroup group) {
    return Permission.ofDefault(PermissionRegistry.get(), name, group).getIdentifier();
  }

}
