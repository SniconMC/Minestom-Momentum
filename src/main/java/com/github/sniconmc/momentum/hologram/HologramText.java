package com.github.sniconmc.momentum.hologram;

import com.github.sniconmc.utils.text.TextUtils;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Metadata;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.metadata.display.AbstractDisplayMeta;
import net.minestom.server.entity.metadata.display.TextDisplayMeta;
import net.minestom.server.network.packet.server.play.DestroyEntitiesPacket;
import net.minestom.server.network.packet.server.play.EntityMetaDataPacket;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/**
 * Represents a hologram text entity that can be displayed to players.
 * Provides functionality to make hologram text visible or despawn it for specific players.
 *
 * @see TextUtils
 * @see Player
 * @see Entity
 * @see Pos
 *
 * @author znopp and Wi1helm
 */
public class HologramText extends Entity {

    private String uuid;
    private Integer rowId;
    private int entityId;
    private Pos parentPos;
    private List<String> text;

    /**
     * Constructs a new {@link HologramText} entity.
     *
     * @param rowId The row ID of the hologram text.
     * @param position The position of the hologram text in the world.
     * @param text The text to display as a hologram.
     */
    public HologramText(int rowId, @NotNull Pos position, List<String> text) {
        super(EntityType.TEXT_DISPLAY);
        this.rowId = rowId;
        this.position = position.add(0,rowId*0.4,0);
        this.text = text;

        editEntityMeta(TextDisplayMeta.class, meta -> {
            meta.setHasNoGravity(true);
            meta.setText(TextUtils.convertStringListToComponent(text));
            meta.setBillboardRenderConstraints(AbstractDisplayMeta.BillboardConstraints.CENTER);
        });
    }

    /**
     * Makes the hologram text visible to a specific player.
     *
     * @param player The player to make the hologram visible to.
     */
    public void makeVisibleTo(@NotNull Player player) {
        Map<Integer, Metadata.Entry<?>> entries = metadata.getEntries();

        // Send the SpawnEntityPacket only to this player
        player.sendPacket(getEntityType().registry().spawnType().getSpawnPacket(this));
        player.sendPacket(new EntityMetaDataPacket(this.getEntityId(), entries));
    }

    /**
     * Despawns the hologram text for a specific player.
     *
     * @param player The player to despawn the hologram for.
     */
    public void despawnForPlayer(@NotNull Player player) {
        player.sendPacket(new DestroyEntitiesPacket(getEntityId()));
        this.remove();
    }

}
