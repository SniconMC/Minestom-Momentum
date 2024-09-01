package com.github.sniconmc.momentum;

import com.github.sniconmc.momentum.config.MomentumConfig;
import com.github.sniconmc.momentum.data.MomentumConfigHolder;
import com.github.sniconmc.momentum.data.MomentumHologramHolder;
import com.github.sniconmc.utils.placeholder.PlaceholderReplacer;
import com.github.sniconmc.momentum.utils.HologramUtils;
import com.github.sniconmc.momentum.utils.LoadMomentum;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code MomentumManager} class is responsible for managing the core functionality of the Momentum plugin.
 * It handles the loading, reloading, and storage of momentum configuration and hologram data, as well as
 * providing utility methods to initiate pads for players.
 */
public class MomentumManager {

    /**
     * The Gson instance used for JSON serialization and deserialization.
     */
    private static Gson gson = new Gson();

    /**
     * The data folder where the momentum configuration files are stored.
     */
    private static final File dataFolder = new File("resources/momentum");

    /**
     * A map that holds the JSON data of files loaded from the data folder.
     */
    private static Map<String, String> dataFileJSONData;

    /**
     * A map that associates players with their respective hologram holders.
     */
    private static final Map<Player, List<MomentumHologramHolder>> hologramHolder = new HashMap<>();

    /**
     * A map that associates players with their respective configuration holders.
     */
    private static final Map<Player, List<MomentumConfigHolder>> configHolder = new HashMap<>();

    /**
     * Constructs a new {@code MomentumManager} instance and initializes its resources.
     * Loads the data from the specified data folder using {@link LoadMomentum}.
     */
    public MomentumManager() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        dataFileJSONData = new LoadMomentum().load(dataFolder);
    }

    /**
     * Reloads the momentum data from the data folder and updates holograms and pads for all online players.
     */
    public static void reloadMomentum() {
        dataFileJSONData = new LoadMomentum().load(dataFolder);

        for (Player player : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
            HologramUtils.unloadHologramsFromList(HologramUtils.getHologramEntities(player), player);
            initiateMomentumPads(player);
        }
    }

    /**
     * Initializes the momentum pads for a specific player by loading and parsing configuration data,
     * and setting up holograms based on that data.
     *
     * @param player The player for whom the momentum pads should be initiated.
     */
    public static void initiateMomentumPads(Player player) {
        List<MomentumConfigHolder> configHolders = new ArrayList<>();
        List<MomentumHologramHolder> hologramHolders = new ArrayList<>();

        for (String fileName : dataFileJSONData.keySet()) {
            String placeholderReplacedJson = PlaceholderReplacer.replacePlaceholders(player, dataFileJSONData.get(fileName));

            try {
                MomentumConfig config = gson.fromJson(placeholderReplacedJson, MomentumConfig.class);
                configHolders.add(new MomentumConfigHolder(player, fileName, config));
                hologramHolders.add(new MomentumHologramHolder(player, fileName, HologramUtils.loadHolograms(config, player)));
            } catch (JsonSyntaxException | JsonIOException e) {
                MomentumMain.logger.error("Error parsing JSON in: {}", fileName);
            } catch (Exception e) {
                MomentumMain.logger.error("Unexpected error in: {}", fileName);
            }
        }

        hologramHolder.put(player, hologramHolders);
        configHolder.put(player, configHolders);
    }

    /**
     * Returns a map containing players and their associated configuration holders.
     *
     * @return A map of players and their {@link MomentumConfigHolder} lists.
     */
    public static Map<Player, List<MomentumConfigHolder>> getConfigHolder() {
        return configHolder;
    }

    /**
     * Returns a map containing players and their associated hologram holders.
     *
     * @return A map of players and their {@link MomentumHologramHolder} lists.
     */
    public static Map<Player, List<MomentumHologramHolder>> getHologramHolder() {
        return hologramHolder;
    }
}
