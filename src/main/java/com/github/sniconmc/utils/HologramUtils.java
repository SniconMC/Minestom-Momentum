package com.github.sniconmc.utils;

import com.github.sniconmc.MomentumManager;
import com.github.sniconmc.config.MomentumConfig;
import com.github.sniconmc.data.MomentumHologramHolder;
import com.github.sniconmc.hologram.HologramText;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HologramUtils {


    public static List<Entity> loadHolograms(MomentumConfig config, Player player) {

        List<Entity> entities = new ArrayList<>();

        int size = config.getDisplay().getText_list().size();

        for (int i = 0; i < size; i++) {

             int index = (size-1) - i;

             List<String> text = config.getDisplay().getText_list().get(index);

            HologramText hologramEntity = new HologramText(i, config.getCorners().getMiddle(), text);
            hologramEntity.makeVisibleTo(player);
            entities.add(hologramEntity);

        }
    return entities;
    }

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
