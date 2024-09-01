package com.github.sniconmc.momentum.listener;

import com.github.sniconmc.momentum.MomentumExecutor;
import com.github.sniconmc.momentum.MomentumManager;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerMoveEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;

/**
 * Handles event listeners for the Momentum server.
 * Listens to player move and spawn events to execute related logic.
 *
 * @see MomentumExecutor
 * @see MomentumManager
 * @see PlayerMoveEvent
 * @see PlayerSpawnEvent
 *
 * @author znopp and Wi1helm
 */
public class MomentumListener {
    private final GlobalEventHandler momentumNode;

    /**
     * Initializes the event listener for Momentum-related events.
     */
    public MomentumListener() {
        this.momentumNode = MinecraftServer.getGlobalEventHandler();
        onPlayerMove();
        onPlayerJoin();
    }

    /**
     * Registers a listener for player movement events.
     */
    public void onPlayerMove() {
        momentumNode.addListener(PlayerMoveEvent.class, event -> {
            Player player = event.getPlayer();
            MomentumExecutor.executor(player);
        });
    }

    /**
     * Registers a listener for player join events.
     */
    private void onPlayerJoin() {
        momentumNode.addListener(PlayerSpawnEvent.class, event -> {
            Player player = event.getPlayer();
            MomentumManager.initiateMomentumPads(player);
        });
    }
}
