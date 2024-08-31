package com.github.sniconmc.utils;

import com.github.sniconmc.config.MomentumConfig;

public class PadType {

    public static String determinePadType(MomentumConfig config) {
        if (config.getDestination_corners() != null) {
            return "telepad";
        } else if (config.getDirectional_strength() != null && config.getVertical_strength() != null) {
            return "launchpad";
        }
        return "unknown";
    }

}
