package com.github.sniconmc.momentum.utils;

import com.github.sniconmc.momentum.MomentumMain;
import com.github.sniconmc.momentum.config.MomentumConfig;
import net.minestom.server.coordinate.Pos;

/**
 * Utility class for bounding box related operations.
 * Provides methods to check if a player's position is within a defined bounding box.
 *
 * @see MomentumConfig
 * @see Pos
 * @see MomentumMain
 *
 * @author znopp and Wi1helm
 */
public class BoundingBox {

    /**
     * Checks if a player's location is within the configured bounds.
     *
     * @param config The configuration containing the corner positions defining the bounds.
     * @param playerLocation The player's current position.
     * @param checkY Whether to check the Y-coordinate bounds or not.
     * @return {@code true} if the player's location is within the bounds; {@code false} otherwise.
     */
    public static boolean isWithinBounds(MomentumConfig config, Pos playerLocation, boolean checkY) {
        Pos corner1 = config.getCorners().getCorner1();
        Pos corner2 = config.getCorners().getCorner2();

        int minX = Math.min(corner1.blockX(), corner2.blockX());
        int maxX = Math.max(corner1.blockX(), corner2.blockX()) + 1;
        int minZ = Math.min(corner1.blockZ(), corner2.blockZ());
        int maxZ = Math.max(corner1.blockZ(), corner2.blockZ()) + 1;

        if (checkY) {
            double minY = Math.min(corner1.blockY(), corner2.blockY());
            double maxY = Math.max(corner1.blockY(), corner2.blockY()) + 1;

            return playerLocation.x() >= minX && playerLocation.x() <= maxX &&
                    playerLocation.y() >= minY && playerLocation.y() <= maxY &&
                    playerLocation.z() >= minZ && playerLocation.z() <= maxZ;
        } else {
            MomentumMain.logger.debug("didn't check Y");
            return playerLocation.x() >= minX && playerLocation.x() <= maxX &&
                    playerLocation.z() >= minZ && playerLocation.z() <= maxZ;
        }
    }

}
