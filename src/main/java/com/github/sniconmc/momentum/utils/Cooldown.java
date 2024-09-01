package com.github.sniconmc.momentum.utils;

import net.minestom.server.entity.Player;

import java.util.HashMap;
import java.util.Map;


/**
 * The {@code Cooldown} class provides utility methods to manage cooldown periods for different momentum pad types.
 * It ensures that players must wait for a certain period before interacting with the same type of pad again.
 */
public class Cooldown {

    /**
     * Checks if the player is currently on cooldown for a specific type of pad.
     *
     * @param player          The player to check.
     * @param type            The type of pad (e.g., "telepad", "launchpad").
     * @param playerCooldowns A map that stores player cooldown data.
     * @return {@code true} if the player is on cooldown; {@code false} otherwise.
     */
    public static boolean isOnCooldown(Player player, String type, Map<Player, Map<String, Long>> playerCooldowns) {
        Map<String, Long> cooldowns = playerCooldowns.get(player);
        if (cooldowns == null) {
            return true; // No cooldown set, player can use the telepad
        }

        Long cooldownEnd = cooldowns.get(type);
        if (cooldownEnd == null) {
            return true; // No cooldown for this type
        }

        long currentTime = System.currentTimeMillis();
        return currentTime >= cooldownEnd; // Check if the cooldown has expired
    }


    /**
     * Sets a cooldown for a player for a specific pad type.
     *
     * @param player           The player to set the cooldown for.
     * @param type             The type of pad (e.g., "telepad", "launchpad").
     * @param cooldownDuration The duration of the cooldown in milliseconds.
     * @param playerCooldowns  A map that stores player cooldown data.
     */

    public static void setCooldown(Player player, String type, long cooldownDuration, Map<Player, Map<String, Long>> playerCooldowns) {
        long cooldownEnd = System.currentTimeMillis() + cooldownDuration;
        Map<String, Long> cooldowns = playerCooldowns.getOrDefault(player, new HashMap<>());
        cooldowns.put(type, cooldownEnd);
        playerCooldowns.put(player, cooldowns); // Set cooldown end time for the specific type
    }

}
