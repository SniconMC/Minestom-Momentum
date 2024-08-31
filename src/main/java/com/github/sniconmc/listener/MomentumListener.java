package com.github.sniconmc.listener;

import com.github.sniconmc.MomentumExecutor;
import com.github.sniconmc.MomentumManager;
import com.github.sniconmc.data.MomentumHologramHolder;
import com.github.sniconmc.utils.HologramUtils;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerMoveEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;

import java.util.List;

public class MomentumListener {
    private final GlobalEventHandler momentumNode;

    public MomentumListener() {
        this.momentumNode = MinecraftServer.getGlobalEventHandler();
        onPlayerMove();
        onPlayerJoin();
    }

    public void onPlayerMove(){
        momentumNode.addListener(PlayerMoveEvent.class, event -> {
            Player player = event.getPlayer();

            MomentumExecutor.executor(player);

        });
    }

    private void onPlayerJoin() {
        momentumNode.addListener(PlayerSpawnEvent.class, event -> {
            Player player = event.getPlayer();

            HologramUtils.unloadHologramsFromList(HologramUtils.getHologramEntities(player), player);

            MomentumManager.initiateMomentumPads(player);
        });
    }
}
