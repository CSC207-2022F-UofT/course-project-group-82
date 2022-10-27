package com.ouieeat.ouieat;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OuiLogger {
    public static Logger logger = LogManager.getRootLogger();

    public static void log(Level level, String message){
        logger.log(level, message);
    }
}
