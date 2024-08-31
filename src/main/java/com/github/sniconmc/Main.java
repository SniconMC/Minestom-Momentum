package com.github.sniconmc;

import com.github.sniconmc.listener.MomentumListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        logger.info("Momentum initialized");

        MomentumManager momentumManager = new MomentumManager();

        MomentumListener momentumListener = new MomentumListener();
    }
}