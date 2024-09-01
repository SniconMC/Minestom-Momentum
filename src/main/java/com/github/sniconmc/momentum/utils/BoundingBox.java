package com.github.sniconmc.momentum.utils;

import com.github.sniconmc.momentum.MomentumMain;
import com.github.sniconmc.momentum.config.MomentumConfig;
import net.minestom.server.coordinate.Pos;

public class BoundingBox {

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
