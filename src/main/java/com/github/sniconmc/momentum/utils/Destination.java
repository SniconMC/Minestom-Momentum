package com.github.sniconmc.momentum.utils;

import com.github.sniconmc.momentum.MomentumMain;
import com.github.sniconmc.momentum.config.MomentumConfig;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;

/**
 * Utility class for calculating teleport destinations.
 * Provides methods to determine a teleport location based on configuration.
 *
 * @see MomentumConfig
 * @see Pos
 * @see Player
 * @see MomentumMain
 *
 * @author znopp and Wi1helm
 */
public class Destination {

    /**
     * Computes the destination position for teleportation.
     *
     * @param config The configuration file to read settings from.
     * @param corner1 The first corner position.
     * @param corner2 The second corner position.
     * @param player The player to teleport.
     * @param fileName The name of the file containing the teleport configuration.
     * @return The calculated teleport destination as a {@link Pos}.
     */
    public static Pos getTeleportDestination(MomentumConfig config, Pos corner1, Pos corner2, Player player, String fileName) {
        int destination1X = corner1.blockX();
        int destination1Y = corner1.blockY();
        int destination1Z = corner1.blockZ();

        int destination2X = corner2.blockX();
        int destination2Y = corner2.blockY();
        int destination2Z = corner2.blockZ();

        double finalY;

        String isPortal = config.is_portal();

        if ("true".equalsIgnoreCase(isPortal)) {

            // TODO: telepad height check
            //  implement ability to jump into portal and be teleported to the right place
            //  but with your jump offset included

            // Teleport the player to the bottom Y level
            finalY = Math.min(corner1.blockY(), corner2.blockY());
        } else if ("false".equalsIgnoreCase(isPortal)) {
            // Y coordinate defaulting to smaller value if not a portal
            if (destination1Y != destination2Y) {
                MomentumMain.logger.warn("Telepad '" + fileName + "' is not a portal, but still has differing Y destinations!");
                MomentumMain.logger.warn("Y coordinate defaulting to smaller value");
            }
            finalY = Math.min(corner1.blockY(), corner2.blockY()) + 1;
        } else if (isPortal == null) {
            MomentumMain.logger.warn("Telepad type not declared in the JSON file! Assuming horizontal...");
            finalY = Math.min(corner1.blockY(), corner2.blockY()) + 1;
        } else {
            MomentumMain.logger.warn("Telepad '" + fileName + "' has invalid is_portal value '" + isPortal + "'. Assuming horizontal...");
            finalY = Math.min(corner1.blockY(), corner2.blockY()) + 1;
        }

        double finalX = ((double) (destination1X + destination2X) / 2) + 0.5;
        double finalZ = ((double) (destination1Z + destination2Z) / 2) + 0.5;

        return new Pos(finalX, finalY, finalZ, config.getTeleport_yaw(), player.getPosition().pitch());
    }
}
