package gg.eris.lobby.npcs;

import lombok.Getter;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

// Subclasses of this will contain the relevant right-click events for the given NPC.
public abstract class ErisBaseLobbyNpc {

  @Getter
  private boolean spawned;

  private final NPC citizensNpc;

  public ErisBaseLobbyNpc() {
    this.citizensNpc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, getIgn());

    SkinTrait skinTrait = this.citizensNpc.getOrAddTrait(SkinTrait.class);
    skinTrait.setSkinName(getSkinName());
  }

  public boolean matches(NPC npc) {
    return citizensNpc == npc;
  }

  public void spawn(Location location) {
    spawned = true;
    citizensNpc.spawn(location);
  }

  public void despawn() {
    spawned = false;
    citizensNpc.despawn();
  }

  public void teleport(Location location) {
    citizensNpc.teleport(location, TeleportCause.PLUGIN);
  }

  public abstract String getIgn();

  public abstract String getId();

  public abstract String getSkinName();

  public abstract void onRightClickedByPlayer(Player player);
}
