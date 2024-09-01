package com.github.sniconmc.momentum;

import com.github.sniconmc.momentum.commands.MomentumCommand;
import com.github.sniconmc.momentum.listener.MomentumListener;
import net.minestom.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@code MomentumMain} class is the entry point for initializing the Momentum plugin.
 * It sets up the command manager, registers event listeners, and initializes core components
 * necessary for the plugin's functionality.
 */
public class MomentumMain {

    /**
     * A {@link Logger} instance used to log messages to the console.
     */
    public static final Logger logger = LoggerFactory.getLogger(MomentumMain.class);

    /**
     * Initializes the Momentum plugin by registering commands and listeners.
     * This method sets up the necessary infrastructure for Momentum to function within
     * the Minestom server environment.
     */
    public static void init() {
        logger.info("Momentum initialized");

        // Initialize the MomentumManager to handle core functionalities
        MomentumManager momentumManager = new MomentumManager();

        // Register commands
        MinecraftServer.getCommandManager().register(new MomentumCommand());

        // Initialize and register listeners
        MomentumListener momentumListener = new MomentumListener();
    }
}
