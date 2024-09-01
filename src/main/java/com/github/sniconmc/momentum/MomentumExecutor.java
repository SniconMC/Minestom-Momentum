package com.github.sniconmc.momentum;

import com.github.sniconmc.momentum.config.MomentumConfig;
import com.github.sniconmc.momentum.config.MomentumCoordinates;
import com.github.sniconmc.momentum.data.MomentumConfigHolder;
import com.github.sniconmc.utils.sound.SoundUtils;
import com.github.sniconmc.momentum.utils.BoundingBox;
import com.github.sniconmc.momentum.utils.Cooldown;
import com.github.sniconmc.momentum.utils.Destination;
import com.github.sniconmc.momentum.utils.PadType;
import net.kyori.adventure.sound.Sound;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code MomentumExecutor} class is responsible for executing the behavior of momentum pads for players.
 * It handles different pad types such as "telepad" and "launchpad," and manages player cooldowns
 * to ensure proper functionality.
 */
public class MomentumExecutor {

    /**
     * A map storing player cooldowns for different pad types.
     */
    private static final Map<Player, Map<String, Long>> playerCooldowns = new HashMap<>();

    /**
     * A map storing the previous positions of players to ensure correct teleportation logic.
     */
    private static final Map<Player, Pos> oldPos = new HashMap<>();

    /**
     * Executes the momentum logic for a player, iterating through their associated configurations
     * and checking if they are on a momentum pad.
     *
     * @param player The player to execute momentum logic for.
     */
    public static void executor(Player player) {
        List<MomentumConfigHolder> configHolderList = MomentumManager.getConfigHolder().get(player);

        for (MomentumConfigHolder configHolder : configHolderList) {
            MomentumConfig config = configHolder.getConfig();
            String filename = configHolder.getFileName();
            isOnMomentumPad(config, player, filename);
        }
    }

    /**
     * Checks if the player is on a momentum pad and executes the appropriate action based on the pad type.
     *
     * @param config   The configuration of the momentum pad.
     * @param player   The player to check.
     * @param filename The name of the configuration file.
     */
    public static void isOnMomentumPad(MomentumConfig config, Player player, String filename) {
        Pos playerPos = player.getPosition();

        if (!BoundingBox.isWithinBounds(config, playerPos, true)) {
            return;
        }

        String type = PadType.determinePadType(config);

        if (!Cooldown.isOnCooldown(player, type, playerCooldowns)) {
            return;
        }

        executeMomentum(config, player, type, filename);
    }

    /**
     * Executes the momentum action for a player based on the pad type.
     * Handles both "telepad" and "launchpad" types, setting the appropriate cooldowns and triggering actions.
     *
     * @param config   The configuration of the momentum pad.
     * @param player   The player to execute the action for.
     * @param type     The type of the momentum pad.
     * @param filename The name of the configuration file.
     */
    private static void executeMomentum(MomentumConfig config, Player player, String type, String filename) {
        try {
            if ("telepad".equals(type) && config.getDestination_corners() != null) {
                Pos playerPos = player.getPosition();
                Pos comparedPos = oldPos.get(player);

                if (comparedPos == null || !playerPos.sameView(comparedPos.yaw(), comparedPos.pitch())) {
                    oldPos.put(player, playerPos);
                    return;
                }

                MomentumCoordinates coordinates = config.getDestination_corners();
                Pos corner1 = coordinates.getCorner1();
                Pos corner2 = coordinates.getCorner2();
                Pos destination = Destination.getTeleportDestination(config, corner1, corner2, player, filename);

                MinecraftServer.getSchedulerManager().scheduleNextTick(() -> player.teleport(destination));
                Cooldown.setCooldown(player, "telepad", config.getCooldown(), playerCooldowns);
            } else if ("unknown".equals(type)) {
                MomentumMain.logger.error("Type required was telepad, but only {} was found!", type);
                return;
            }
        } catch (NullPointerException e) {
            MomentumMain.logger.error("Data in {} file missing! Error: {}", filename, e.getMessage());
            return;
        }

        Double vertical_strength = config.getVertical_strength();
        Double directional_strength = config.getDirectional_strength();

        try {
            if ("launchpad".equals(type) && vertical_strength != null && directional_strength != null) {
                Pos playerLocation = player.getPosition();
                Vec vector = playerLocation.direction().mul(directional_strength * 20).withY(vertical_strength * 20);
                player.setVelocity(vector);

                if (config.getCooldown() == null) {
                    MomentumMain.logger.error("Cooldown missing! Setting to 200...");
                    Cooldown.setCooldown(player, "launchpad", 200, playerCooldowns);
                } else {
                    Cooldown.setCooldown(player, "launchpad", config.getCooldown(), playerCooldowns);
                }
            } else if ("unknown".equals(type)) {
                MomentumMain.logger.error("Type required was launchpad, but only {} was found!", type);
                return;
            }
        } catch (NullPointerException e) {
            MomentumMain.logger.error("Strength value(s) missing! Error: {}", e.getMessage());
            return;
        }

        String soundEvent = config.getSound().getSound_event();
        float volume = config.getSound().getVolume();
        float pitch = config.getSound().getPitch();
        player.playSound(Sound.sound(SoundUtils.stringToSoundEvent(soundEvent), Sound.Source.MASTER, volume, pitch));
    }
}
