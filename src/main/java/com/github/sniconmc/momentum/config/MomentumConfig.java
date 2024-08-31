package com.github.sniconmc.momentum.config;

/**
 * Represents the configuration for a momentum system.
 */
public class MomentumConfig {

    private MomentumCoordinates corners;
    private MomentumCoordinates destination_corners;
    private MomentumDisplay display;
    private MomentumSounds sound;

    private Double directional_strength;
    private Double vertical_strength;
    private String world;
    private float teleport_yaw;
    private Long cooldown;
    private String is_portal;

    /**
     * Gets the corners of the momentum area.
     * @return The MomentumCoordinates representing the corners.
     */
    public MomentumCoordinates getCorners() {
        return corners;
    }

    /**
     * Gets the corners of the destination area.
     * @return The MomentumCoordinates representing the destination corners.
     */
    public MomentumCoordinates getDestination_corners() {
        return destination_corners;
    }

    /**
     * Gets the display configuration.
     * @return The MomentumDisplay object.
     */
    public MomentumDisplay getDisplay() {
        return display;
    }

    /**
     * Gets the directional strength of the momentum.
     * @return The directional strength as a Double.
     */
    public Double getDirectional_strength() {
        return directional_strength;
    }

    /**
     * Gets the vertical strength of the momentum.
     * @return The vertical strength as a Double.
     */
    public Double getVertical_strength() {
        return vertical_strength;
    }

    /**
     * Gets the world name.
     * @return The world name as a String.
     */
    public String getWorld() {
        return world;
    }

    /**
     * Gets the teleport yaw.
     * @return The teleport yaw as a float.
     */
    public float getTeleport_yaw() {
        return teleport_yaw;
    }

    /**
     * Gets the cooldown duration.
     * @return The cooldown duration as a Long.
     */
    public Long getCooldown() {
        return cooldown;
    }

    /**
     * Gets the sound configuration.
     * @return The MomentumSounds object.
     */
    public MomentumSounds getSound() {
        return sound;
    }

    /**
     * Checks if this configuration represents a portal.
     * @return A String indicating whether this is a portal.
     */
    public String is_portal() {
        return is_portal;
    }
}