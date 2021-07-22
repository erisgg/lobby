package gg.eris.lobby.npcs;

import lombok.Getter;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.plugin.Plugin;

// Subclasses of this will contain the relevant right-click events for the given NPC.
public abstract class ErisLobbyNPC implements Listener {

  @Getter
  private boolean spawned;

  private final NPC citizensNpc;

  public ErisLobbyNPC(Plugin plugin) {
    Bukkit.getServer().getPluginManager().registerEvents(this, plugin);

    this.citizensNpc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, getIgn());

    SkinTrait skinTrait = this.citizensNpc.getOrAddTrait(SkinTrait.class);
    skinTrait.setSkinName(getSkinName());
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
}
