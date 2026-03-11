package com.petstore.hooks;

import io.cucumber.java.Before;
import net.serenitybdd.rest.SerenityRest;
import io.restassured.RestAssured;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import com.petstore.util.Constantes;

public class Hooks {

    @Before
    public void beforeAll() {
        OnStage.setTheStage(new OnlineCast());
        RestAssured.baseURI = Constantes.URL_PETSTORE;
        SerenityRest.useRelaxedHTTPSValidation();
    }
}
