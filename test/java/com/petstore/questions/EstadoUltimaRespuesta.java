package com.petstore.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.rest.SerenityRest;
import io.restassured.response.Response;

public class EstadoUltimaRespuesta implements Question<Integer> {

    public static EstadoUltimaRespuesta codigo() {
        return new EstadoUltimaRespuesta();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        Response r = SerenityRest.lastResponse();
        return (r != null) ? r.getStatusCode() : null;
    }
}
