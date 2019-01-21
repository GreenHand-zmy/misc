package com.peter.openx;

import java.util.Properties;

public class Environment {
    /**
     * Save the internal configuration
     */
    private Properties properties = new Properties();

    /**
     * create an empty environment
     *
     * @return
     */
    public static Environment empty() {
        return new Environment();
    }

    /**
     * Properties to Environment
     *
     * @param props
     * @return
     */
    public static Environment of(Properties props) {
        Environment environment = new Environment();
        environment.properties = props;
        return environment;
    }
}
