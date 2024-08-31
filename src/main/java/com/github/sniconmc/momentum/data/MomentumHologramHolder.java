package com.github.sniconmc.momentum.data;

import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;

import java.util.List;

/**
 * The {@code MomentumHologramHolder} class serves as a data container that links a player to a list of hologram entities
 * within a Minestom server environment. This class associates a player with a specific hologram configuration file name
 * and the list of entities representing the holograms.
 * <p>
 * This class is useful for managing and accessing hologram entities associated with individual players, making it easier
 * to store and manipulate player-specific holograms.
 * </p>
 */
public class MomentumHologramHolder {

    private Player player;
    private String fileName;
    private List<Entity> entities;

    /**
     * Constructs a new {@code MomentumHologramHolder} instance, associating a player with a specific hologram configuration
     * file name and a list of entities representing the holograms.
     *
     * @param player   The player associated with this hologram holder.
     * @param fileName The name of the configuration file related to the player's holograms.
     * @param entities The list of {@link Entity} objects representing the holograms associated with the player.
     */
    public MomentumHologramHolder(Player player, String fileName, List<Entity> entities) {
        this.player = player;
        this.fileName = fileName;
        this.entities = entities;
    }

    /**
     * Returns the player associated with this hologram holder.
     *
     * @return The {@link Player} instance associated with this hologram holder.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the name of the configuration file associated with the player's holograms.
     *
     * @return The name of the configuration file as a {@link String}.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Returns the list of entities representing the player's holograms.
     *
     * @return A list of {@link Entity} objects associated with the player's holograms.
     */
    public List<Entity> getEntities() {
        return entities;
    }
}
