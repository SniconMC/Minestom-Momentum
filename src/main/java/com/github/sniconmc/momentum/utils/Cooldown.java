package com.github.sniconmc.momentum.utils;

import net.minestom.server.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Cooldown {

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

    public static void setCooldown(Player player, String type, long cooldownDuration, Map<Player, Map<String, Long>> playerCooldowns) {
        long cooldownEnd = System.currentTimeMillis() + cooldownDuration;
        Map<String, Long> cooldowns = playerCooldowns.getOrDefault(player, new HashMap<>());
        cooldowns.put(type, cooldownEnd);
        playerCooldowns.put(player, cooldowns); // Set cooldown end time for the specific type
    }

}
