package com.github.sniconmc.momentum;

import com.github.sniconmc.momentum.data.MomentumHologramHolder;
import com.github.sniconmc.momentum.listener.MomentumListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {

    public static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        logger.info("Momentum initialized");

        MomentumManager momentumManager = new MomentumManager();

        /*MomentumListener momentumListener = new MomentumListener();*/
    }

/*    public static Main init() {

        logger.info("Momentum initialized");

        MomentumManager momentumManager = new MomentumManager();

        MomentumListener momentumListener = new MomentumListener();
        return new Main();
    }*/
}