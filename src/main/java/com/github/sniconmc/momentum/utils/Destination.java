package com.github.sniconmc.momentum.utils;

import com.github.sniconmc.momentum.Main;
import com.github.sniconmc.momentum.config.MomentumConfig;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;

public class Destination {

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
            // it is a portal, therefore teleport the player to the bottom Y level

            // TODO: telepad height check
            //  implement ability to jump into portal and be teleported to the right place
            //  but with your jump offset included

            finalY = Math.min(corner1.blockY(), corner2.blockY());
        } else if ("false".equalsIgnoreCase(isPortal)) {
            // not a portal
            if (destination1Y != destination2Y) {
                // still has differing Y levels
                Main.logger.warn("Telepad '" + fileName + "' is not a portal, but still has differing Y destinations!");
                Main.logger.warn("Y coordinate defaulting to smaller value");
            }
            finalY = Math.min(corner1.blockY(), corner2.blockY()) + 1;
        } else if (isPortal == null) {
            // Handle the case where is_portal is missing
            Main.logger.warn("Telepad type not declared in the JSON file! Assuming horizontal...");
            finalY = Math.min(corner1.blockY(), corner2.blockY()) + 1;
        } else {
            // Handle the case where is_portal is incorrectly set (e.g., "flase")
            Main.logger.warn("Telepad '" + fileName + "' has invalid is_portal value '" + isPortal + "'. Assuming horizontal...");
            finalY = Math.min(corner1.blockY(), corner2.blockY()) + 1;
        }

        double finalX = ((double) (destination1X + destination2X) / 2) + 0.5;
        double finalZ = ((double) (destination1Z + destination2Z) / 2) + 0.5;

        return new Pos(finalX, finalY, finalZ, config.getTeleport_yaw(), player.getPosition().pitch());
    }

}
