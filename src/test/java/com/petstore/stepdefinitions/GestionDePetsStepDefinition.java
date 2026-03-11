package com.petstore.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.rest.SerenityRest;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

import com.petstore.tasks.CrearMascota;
import com.petstore.tasks.ObtenerMascotaPorId;
import com.petstore.tasks.EliminarMascota;
import com.petstore.tasks.LlamarApi;
import com.petstore.tasks.ListarMascotasPorEstado;
import com.petstore.questions.EstadoUltimaRespuesta;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GestionDePetsStepDefinition {

    @Given("el servicio Petstore está disponible")
    public void servicio_disponible() {
        OnStage.setTheStage(new OnlineCast());
        Actor actor = OnStage.theActorCalled("cliente");
    }

    @Given("el body contiene una mascota válida")
    public void body_mascota_valida() {
        Actor actor = OnStage.theActorInTheSpotlight();
        Map<String, Object> pet = new HashMap<>();
        pet.put("id", 999999);
        pet.put("name", "mascota-ejemplo");
        pet.put("status", "available");
        actor.remember("petBody", pet);
    }

    @Given("^existe una mascota con id (\\d+)$")
    public void existe_mascota_con_id(Integer id) {
        Actor actor = OnStage.theActorInTheSpotlight();
        Map<String, Object> pet = new HashMap<>();
        pet.put("id", id);
        pet.put("name", "mascota-" + id);
        pet.put("status", "available");
        actor.attemptsTo(CrearMascota.conDatos(pet));
    }


    @When("^el usuario envía POST /pet$")
    public void usuario_envia_post_pet() {
        Actor actor = OnStage.theActorInTheSpotlight();
        Object pet = actor.recall("petBody");
        actor.attemptsTo(CrearMascota.conDatos(pet));
    }

    @When("^el usuario envía GET /pet/(\\d+)$")
    public void usuario_envia_get_pet(Integer id) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(ObtenerMascotaPorId.conId(String.valueOf(id)));
    }

    @When("^el usuario envía POST /pet/(\\d+) con name y status$")
    public void usuario_envia_post_pet_con_name_y_status(Integer id) {
        String url = com.petstore.util.Constantes.URL_PETSTORE + "/pet/" + id;
        net.serenitybdd.rest.SerenityRest.given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("name", "nuevo-nombre")
                .formParam("status", "sold")
                .post(url);
    }

    @When("^el usuario envía DELETE /pet/(\\d+)$")
    public void usuario_envia_delete_pet(Integer id) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(EliminarMascota.conId(String.valueOf(id)));
    }

    @Then("el sistema devuelve código {int}")
    public void sistema_devuelve_codigo(Integer expectedCode) {
        Actor actor = OnStage.theActorInTheSpotlight();
        Integer code = EstadoUltimaRespuesta.codigo().answeredBy(actor);
        assertThat(code, is(expectedCode));
    }

    @Then("la respuesta contiene los datos de la mascota")
    public void respuesta_contiene_datos_mascota() {
        Response r = SerenityRest.lastResponse();
        assertThat(r, notNullValue());
        Integer id = r.jsonPath().getInt("id");
        assertThat(id, notNullValue());
    }

    @Then("la mascota deja de existir en el sistema")
    public void mascota_deja_de_existir() {
        Response r = SerenityRest.lastResponse();
        String message = r.jsonPath().getString("message");
        Integer id = (message != null) ? null : r.jsonPath().getInt("id");
    }
}
