package com.github.sniconmc.config;

import net.minestom.server.coordinate.Pos;

/**
 * Represents a set of coordinates for a momentum area.
 */
public class MomentumCoordinates {

    private int x1;
    private int y1;
    private int z1;

    private int x2;
    private int y2;
    private int z2;

    /**
     * Gets the first corner of the momentum area.
     * @return A Pos object representing the first corner.
     */
    public Pos getCorner1() {
        return new Pos(x1, y1, z1);
    }

    /**
     * Gets the second corner of the momentum area.
     * @return A Pos object representing the second corner.
     */
    public Pos getCorner2() {
        return new Pos(x2, y2, z2);
    }

    /**
     * Calculates and returns the middle point of the momentum area.
     * The middle point is calculated as the average of x and z coordinates,
     * and 2 blocks above the higher y coordinate.
     * @return A Pos object representing the middle point.
     */
    public Pos getMiddle() {
        return new Pos((double) (x1 + x2 + 1) /2, Math.max(y1, y2) + 2, (double) (z1 + z2 + 1) /2);
    }
}