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

public class MomentumExecutor {
    private static final Map<Player, Map<String, Long>> playerCooldowns = new HashMap<>();
    private static final Map<Player, Pos> oldPos = new HashMap<>();

    public static void executor(Player player){

        List<MomentumConfigHolder> configHolderList = MomentumManager.getConfigHolder().get(player);

        for (MomentumConfigHolder configHolder : configHolderList) {
            MomentumConfig config = configHolder.getConfig();
            String filename = configHolder.getFileName();

            isOnMomentumPad(config, player, filename);
        }
    }


    public static void isOnMomentumPad(MomentumConfig config, Player player, String filename) {

        Pos playerPos = player.getPosition();
        Pos comparedPos = oldPos.get(player);

        if(comparedPos == null || !playerPos.sameView(comparedPos.yaw(), comparedPos.pitch())){
            oldPos.put(player, playerPos);
            return;
        }

        if (!BoundingBox.isWithinBounds(config, playerPos, true)) {
            return;
        }

        String type = PadType.determinePadType(config);

        if (!Cooldown.isOnCooldown(player, type, playerCooldowns)) {
            return;
        }

        executeMomentum(config, player, type, filename);

    }

    private static void executeMomentum(MomentumConfig config, Player player, String type, String filename) {

        try {
            if ("telepad".equals(type) && config.getDestination_corners() != null) {
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
            MomentumMain.logger.error("Destination corners missing! Error: {}", e.getMessage());
        }

        Double vertical_strength = config.getVertical_strength();
        Double directional_strength = config.getDirectional_strength();

        try {
            if ("launchpad".equals(type) && vertical_strength != null && directional_strength != null) {
                Pos playerLocation = player.getPosition();

                Vec vector = playerLocation.direction().mul(directional_strength * 20).withY(vertical_strength * 20);
                player.setVelocity(vector);

                Cooldown.setCooldown(player, "launchpad", config.getCooldown(), playerCooldowns);
            } else if ("unknown".equals(type)) {
                MomentumMain.logger.error("Type required was launchpad, but only " + type + " was found!");
                return;
            }
        } catch (NullPointerException e) {
            MomentumMain.logger.error("Strength value(s) missing! Error: " + e.getMessage());
        }

        String soundEvent = config.getSound().getSound_event();
        float volume = config.getSound().getVolume();
        float pitch = config.getSound().getPitch();

        player.playSound(Sound.sound(SoundUtils.stringToSoundEvent(soundEvent), Sound.Source.MASTER, volume, pitch));
    }
}
