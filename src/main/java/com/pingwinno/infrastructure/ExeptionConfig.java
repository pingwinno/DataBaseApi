package com.pingwinno.infrastructure;

import org.glassfish.jersey.server.ResourceConfig;

public class ExeptionConfig extends ResourceConfig {
    public ExeptionConfig() {
        register(new RestExceptionMapper());
    }
}
