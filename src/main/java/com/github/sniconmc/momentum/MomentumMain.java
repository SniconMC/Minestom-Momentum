package com.github.sniconmc.momentum;

import com.github.sniconmc.momentum.listener.MomentumListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MomentumMain {

    public static final Logger logger = LoggerFactory.getLogger(MomentumMain.class);


    public static void init() {
        logger.info("Momentum initialized");

        MomentumManager momentumManager = new MomentumManager();

        MomentumListener momentumListener = new MomentumListener();
    }
}