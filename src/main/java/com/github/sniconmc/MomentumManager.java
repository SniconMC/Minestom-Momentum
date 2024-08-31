package com.github.sniconmc;

import com.github.sniconmc.config.MomentumConfig;
import com.github.sniconmc.data.MomentumConfigHolder;
import com.github.sniconmc.data.MomentumHologramHolder;
import com.github.sniconmc.placeholder.PlaceholderReplacer;
import com.github.sniconmc.utils.HologramUtils;
import com.github.sniconmc.utils.LoadMomentum;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import net.minestom.server.entity.Player;

import javax.swing.plaf.PanelUI;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MomentumManager {

    // Load new Gson
    private static Gson gson = new Gson();

    private static final File dataFolder = new File("resources/momentum");

    private static Map<String, String> dataFileJSONData;
    private static final Map<Player, List<MomentumHologramHolder>> hologramHolder = new HashMap<>();
    private static final Map<Player, List<MomentumConfigHolder>> configHolder = new HashMap<>();

    public MomentumManager() {

        gson = new GsonBuilder().setPrettyPrinting().create();

        dataFileJSONData = new LoadMomentum().load(dataFolder);

    }

    public static void reloadMomentum(Player player) {

        dataFileJSONData = new LoadMomentum().load(dataFolder);
        initiateMomentumPads(player);

    }

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
                // Handle Gson-specific errors
                Main.logger.error("Error parsing JSON in: {}", fileName);
            } catch (Exception e) {
                // Handle any other unexpected exceptions
                Main.logger.error("Unexpected error in: {}", fileName);
            }
        }

        hologramHolder.put(player, hologramHolders);
        configHolder.put(player, configHolders);

    }

    public static Map<Player, List<MomentumConfigHolder>> getConfigHolder() {
        return configHolder;
    }

    public static Map<Player, List<MomentumHologramHolder>> getHologramHolder() {
        return hologramHolder;
    }

}
