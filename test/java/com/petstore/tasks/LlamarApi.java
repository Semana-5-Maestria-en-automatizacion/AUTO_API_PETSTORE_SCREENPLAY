package com.petstore.tasks;

import com.petstore.util.Constantes;

import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class LlamarApi implements Task {
    private final String metodo;
    private final String endpoint;
    private final Object cuerpo;

    public LlamarApi(String metodo, String endpoint, Object cuerpo) {
        this.metodo = metodo;
        this.endpoint = endpoint;
        this.cuerpo = cuerpo;
    }

    public static LlamarApi request(String metodo, String endpoint) {
        return Tasks.instrumented(LlamarApi.class, metodo, endpoint, null);
    }

    public static LlamarApi requestConCuerpo(String metodo, String endpoint, Object cuerpo) {
        return Tasks.instrumented(LlamarApi.class, metodo, endpoint, cuerpo);
    }

    @Override
    public void performAs(Actor actor) {
        String url = endpoint.startsWith("http") ? endpoint : Constantes.URL_PETSTORE + endpoint;
        RequestSpecification spec = SerenityRest.given().contentType("application/json");
        if (cuerpo != null) {
            spec.body(cuerpo);
        }
        switch (metodo.toUpperCase()) {
            case "POST"   -> spec.post(url);
            case "GET"    -> spec.get(url);
            case "PUT"    -> spec.put(url);
            case "DELETE" -> spec.delete(url);
            default -> throw new IllegalArgumentException("Metodo HTTP no soportado: " + metodo);
        }
    }
}
