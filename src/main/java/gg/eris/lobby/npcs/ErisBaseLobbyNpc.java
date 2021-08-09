package gg.eris.lobby.npcs;

import lombok.Getter;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinLayers;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

// Subclasses of this will contain the relevant right-click events for the given NPC.
public abstract class ErisBaseLobbyNpc {

  @Getter
  private boolean spawned;

  protected final NPC citizensNpc;

  public ErisBaseLobbyNpc() {
    this.citizensNpc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, getIgn());
    this.citizensNpc.getOrAddTrait(SkinTrait.class).setSkinPersistent(
        getIgn(),
        getTextureSignature(),
        getTextureData()
    );
    this.citizensNpc.getOrAddTrait(SkinLayers.class).hideCape();
  }

  public final boolean matches(NPC npc) {
    return citizensNpc == npc;
  }

  public final void spawn(Location location) {
    spawned = true;
    citizensNpc.spawn(location);
  }

  public final void despawn() {
    spawned = false;
    citizensNpc.destroy();
  }

  public final void teleport(Location location) {
    citizensNpc.teleport(location, TeleportCause.PLUGIN);
  }

  public abstract String getIgn();

  public abstract String getId();

  public abstract String getTextureSignature();

  public abstract String getTextureData();

  public abstract void onClickedByPlayer(Player player);
}
