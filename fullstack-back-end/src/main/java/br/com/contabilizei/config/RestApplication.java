package br.com.contabilizei.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;


@ApplicationPath("rest")
public class RestApplication extends ResourceConfig {
    public RestApplication() {
        packages("br.com.contabilizei.resources");
        register(JacksonFeature.class);
    }
}