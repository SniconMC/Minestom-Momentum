package com.github.sniconmc.commands;

import com.github.sniconmc.MomentumManager;
import com.github.sniconmc.text.TextUtils;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;

import java.util.List;

public class MomentumCommand extends Command {

    public MomentumCommand() {
        super("momentum");

        setDefaultExecutor((commandSender, commandContext) -> {
            return;
        });

        var reloadArgument = ArgumentType.Literal("reload");

        addSyntax((commandSender, commandContext) -> {

            if (!(commandSender instanceof Player player)){
                return;
            }

            String actionString = commandContext.get(reloadArgument).toString();

            if (actionString.equalsIgnoreCase("reload")){
                MomentumManager.reloadMomentum(player);
                player.sendMessage(TextUtils.convertStringToComponent(List.of("<green>Reloaded Momentum</green>")));
            }
        }, reloadArgument);
    }
}
