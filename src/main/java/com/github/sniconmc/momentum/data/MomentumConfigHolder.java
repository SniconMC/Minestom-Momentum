package com.github.sniconmc.momentum.data;

import com.github.sniconmc.momentum.config.MomentumConfig;
import net.minestom.server.entity.Player;

/**
 * The {@code MomentumConfigHolder} class is a simple data container designed to hold the configuration data
 * associated with a specific player within a Minestom server environment. It encapsulates the relationship between
 * a player, the name of the configuration file, and the corresponding configuration object.
 * <p>
 * This class allows for easy access and management of a player's configuration data, making it convenient
 * to store and retrieve settings specific to individual players.
 * </p>
 */
public class MomentumConfigHolder {

    private Player player;
    private String fileName;
    private MomentumConfig config;

    /**
     * Constructs a new {@code MomentumConfigHolder} instance, associating a player with a specific configuration file name
     * and the configuration data itself.
     *
     * @param player   The player associated with this configuration.
     * @param fileName The name of the configuration file related to the player.
     * @param config   The {@link MomentumConfig} object that holds the configuration data for the player.
     */
    public MomentumConfigHolder(Player player, String fileName, MomentumConfig config) {
        this.player = player;
        this.fileName = fileName;
        this.config = config;
    }

    /**
     * Returns the player associated with this configuration holder.
     *
     * @return The {@link Player} instance associated with this configuration.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the name of the configuration file associated with the player.
     *
     * @return The name of the configuration file as a {@link String}.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Returns the configuration data associated with the player.
     *
     * @return The {@link MomentumConfig} instance holding the configuration data for the player.
     */
    public MomentumConfig getConfig() {
        return config;
    }
}
