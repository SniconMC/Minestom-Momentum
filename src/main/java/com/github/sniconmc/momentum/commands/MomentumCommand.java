package com.github.sniconmc.momentum.commands;

import com.github.sniconmc.momentum.MomentumManager;
import com.github.sniconmc.utils.text.TextUtils;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;

import java.util.List;

/**
 * Represents the command handler for the "momentum" command.
 * Provides subcommands for interacting with the Momentum plugin.
 *
 * @see MomentumManager
 * @see TextUtils
 * @see Command
 * @see Player
 *
 * @author znopp and Wi1helm
 */
public class MomentumCommand extends Command {

    /**
     * Initializes the "momentum" command with its subcommands and their corresponding logic.
     * Currently supports the "reload" subcommand for reloading Momentum-related configurations.
     */
    public MomentumCommand() {
        super("momentum");

        // Sets the default executor for the "momentum" command when no subcommands are specified.
        setDefaultExecutor((commandSender, commandContext) -> {
            return;  // No action for default executor
        });

        // Argument for the "reload" subcommand
        var reloadArgument = ArgumentType.Literal("reload");

        // Adds the "reload" subcommand syntax and its corresponding execution logic
        addSyntax((commandSender, commandContext) -> {
            if (!(commandSender instanceof Player player)) {
                return;  // Command can only be executed by a player
            }

            String actionString = commandContext.get(reloadArgument).toString();

            // If the "reload" subcommand is specified, reload Momentum configurations
            if (actionString.equalsIgnoreCase("reload")) {
                MomentumManager.reloadMomentum();
                player.sendMessage(TextUtils.convertStringToComponent("<green>Reloaded Momentum</green>"));
            }
        }, reloadArgument);
    }
}
