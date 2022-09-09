package br.com.bookstock.resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/info")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Tag(name = "info", description = "Informação básica sobre a API")
    public Map<String, Object> info() {
    	Map<String, Object> body = new LinkedHashMap<>();

		body.put("api_info", "API de usuarios criada para consumo da aplicação BOOK STOCK Manager");
		body.put("timestamp", new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(new Date()));
		body.put("status", "up");

		return body;
    }
}