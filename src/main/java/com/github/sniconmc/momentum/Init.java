package com.github.sniconmc.momentum;

import com.github.sniconmc.momentum.listener.MomentumListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Init {

    public static final Logger logger = LoggerFactory.getLogger(Init.class);

    public static void main(String[] args) {

        logger.info("Momentum initialized");

        MomentumManager momentumManager = new MomentumManager();

        MomentumListener momentumListener = new MomentumListener();
    }
}