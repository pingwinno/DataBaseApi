package com.pingwinno.application;


import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SettingsProperties {
    private final static String PROPSFILE = "/etc/db/config.prop";
    private final static String TESTPROPSFILE = "config_test.prop";

    private static org.slf4j.Logger log = LoggerFactory.getLogger(SettingsProperties.class.getName());

    private static Properties props;

    private static Properties getProperties() throws IOException {

        boolean isLoaded;
        if (props == null) {
            props = new Properties();
            try {
                props.load(new FileInputStream(new File(TESTPROPSFILE)));
                isLoaded = true;
            } catch (FileNotFoundException e) {
                log.debug("config_test.prop not found");
                isLoaded = false;
            }
            if (!isLoaded) {
                try {
                    props.load(new FileInputStream(new File(PROPSFILE)));
                    isLoaded = true;
                } catch (FileNotFoundException e) {
                    log.debug("config_test.prop not found");
                    isLoaded = false;
                }
            }
            if (!isLoaded) {
                log.error("Config file not found");
                System.exit(1);
            }
        }

        return props;
    }

    public static String getMongoDBAddress() {
        String mongoDBAddress = null;
        try {
            mongoDBAddress = getProperties().getProperty("MongoDBAddress");
        } catch (IOException e) {
            log.error("Can't read MongoDBAddress. {}", e);
        }
        return mongoDBAddress;
    }

    public static String getMongoDBName() {
        String mongoDBName = null;
        try {
            mongoDBName = getProperties().getProperty("MongoDBName");
        } catch (IOException e) {
            log.error("Can't read MongoDBName. {}", e);
        }
        return mongoDBName;
    }
}

