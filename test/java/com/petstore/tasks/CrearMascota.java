package com.petstore.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class CrearMascota implements Task {
    private final Object mascota;

    public CrearMascota(Object mascota) {
        this.mascota = mascota;
    }

    public static CrearMascota conDatos(Object mascota) {
        return Tasks.instrumented(CrearMascota.class, mascota);
    }

    @Override
    public void performAs(Actor actor) {
        actor.attemptsTo(
                LlamarApi.requestConCuerpo("POST", "/pet", mascota)
        );
    }
}
