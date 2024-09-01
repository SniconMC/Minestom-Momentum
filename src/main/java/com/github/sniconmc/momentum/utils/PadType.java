package com.github.sniconmc.momentum.utils;

import com.github.sniconmc.momentum.config.MomentumConfig;

/**
 * The {@code PadType} class provides utility methods to determine the type of momentum pad
 * based on its configuration. It supports different types such as "telepad" and "launchpad".
 */
public class PadType {

    /**
     * Determines the type of the momentum pad based on the provided configuration.
     * The type is inferred from the presence of specific configuration attributes.
     *
     * @param config The configuration object for a momentum pad.
     * @return A string representing the type of the pad: "telepad", "launchpad", or "unknown".
     */
    public static String determinePadType(MomentumConfig config) {
        if (config.getDestination_corners() != null) {
            return "telepad";
        } else if (config.getVertical_strength() != null && config.getDirectional_strength() != null) {
            return "launchpad";
        } else {
            return "unknown";
        }
    }
}
