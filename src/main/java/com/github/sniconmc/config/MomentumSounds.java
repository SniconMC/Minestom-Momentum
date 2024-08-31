package com.github.sniconmc.config;

/**
 * Represents the sound configuration for a momentum area.
 */
public class MomentumSounds {

    private String sound_event;
    private float volume;
    private float pitch;

    /**
     * Gets the sound event name.
     * @return The sound event name as a lowercase String.
     */
    public String getSound_event() {
        return sound_event.toLowerCase();
    }

    /**
     * Gets the volume of the sound.
     * @return The volume as a float.
     */
    public float getVolume() {
        return volume;
    }

    /**
     * Gets the pitch of the sound.
     * @return The pitch as a float.
     */
    public float getPitch() {
        return pitch;
    }
}