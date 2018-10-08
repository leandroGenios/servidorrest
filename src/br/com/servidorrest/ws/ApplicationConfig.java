package br.com.servidorrest.ws;

import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {
        packages("br.com.servidorrest.ws");
    }
}