package com.github.sniconmc.momentum.utils;

import com.github.sniconmc.momentum.MomentumManager;
import com.github.sniconmc.momentum.config.MomentumConfig;
import com.github.sniconmc.momentum.data.MomentumHologramHolder;
import com.github.sniconmc.momentum.hologram.HologramText;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for handling hologram-related operations.
 * Provides methods to load, unload, and retrieve hologram entities for players.
 *
 * @see MomentumConfig
 * @see HologramText
 * @see MomentumManager
 * @see Entity
 *
 * @author znopp and Wi1helm
 */
public class HologramUtils {

    /**
     * Loads holograms from the configuration and makes them visible to a specific player.
     *
     * @param config The configuration containing hologram text information.
     * @param player The player to make the holograms visible to.
     * @return A list of loaded hologram entities.
     */
    public static List<Entity> loadHolograms(MomentumConfig config, Player player) {
        List<Entity> entities = new ArrayList<>();
        int size = config.getDisplay().getText_list().size();

        for (int i = 0; i < size; i++) {
            int index = (size - 1) - i;
            List<String> text = config.getDisplay().getText_list().get(index);

            HologramText hologramEntity = new HologramText(i, config.getCorners().getMiddle(), text);
            hologramEntity.makeVisibleTo(player);
            entities.add(hologramEntity);
        }
        return entities;
    }

    /**
     * Unloads holograms from the player's view.
     *
     * @param entities The list of hologram entities to unload.
     * @param player The player to unload the holograms from.
     */
    public static void unloadHologramsFromList(List<Entity> entities, Player player) {
        if (entities == null || entities.isEmpty()) {
            return;
        }

        for (Entity entity : entities) {
            if (!(entity instanceof HologramText hologramEntity)) {
                return;
            }
            hologramEntity.despawnForPlayer(player);
        }
    }

    /**
     * Retrieves all hologram entities visible to a specific player.
     *
     * @param player The player to get the hologram entities for.
     * @return A list of hologram entities visible to the player.
     */
    public static List<Entity> getHologramEntities(Player player) {
        List<MomentumHologramHolder> holders = MomentumManager.getHologramHolder().get(player);
        List<Entity> allEntities = new ArrayList<>();

        for (MomentumHologramHolder holder : holders) {
            List<Entity> entities = holder.getEntities();
            allEntities.addAll(entities);
        }
        return allEntities;
    }
}
