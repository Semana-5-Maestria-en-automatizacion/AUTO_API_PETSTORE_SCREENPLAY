package com.petstore.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class ActualizarMascota implements Task {
    private final Object mascota;

    public ActualizarMascota(Object mascota) {
        this.mascota = mascota;
    }

    public static ActualizarMascota conDatos(Object mascota) {
        return Tasks.instrumented(ActualizarMascota.class, mascota);
    }

    @Override
    public void performAs(Actor actor) {
        actor.attemptsTo(
                LlamarApi.requestConCuerpo("PUT", "/pet", mascota)
        );
    }
}
