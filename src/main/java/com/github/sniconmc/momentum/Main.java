package com.github.sniconmc.momentum;

import com.github.sniconmc.momentum.listener.MomentumListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static Main init() {

        logger.info("Momentum initialized");

        MomentumManager momentumManager = new MomentumManager();

        MomentumListener momentumListener = new MomentumListener();
        return new Main();
    }
}