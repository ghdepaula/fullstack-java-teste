package br.com.contabilizei.config;

import java.time.YearMonth;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Classe responsável por configurar dados de inicialização da aplicação REST. 
 * 
 * @author Guilherme Henrique de Paula 
 * 
 */
@ApplicationPath("rest")
public class RestApplication extends ResourceConfig {
    public RestApplication() {
        packages("br.com.contabilizei.resources");
        register(JacksonFeature.class);
        register(YearMonth.class);
    }
}