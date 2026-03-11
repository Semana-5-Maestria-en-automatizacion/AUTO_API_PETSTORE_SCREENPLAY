package com.petstore.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class EliminarMascota implements Task {
    private final String id;

    public EliminarMascota(String id) {
        this.id = id;
    }

    public static EliminarMascota conId(String id) {
        return Tasks.instrumented(EliminarMascota.class, id);
    }

    @Override
    public void performAs(Actor actor) {
        actor.attemptsTo(
                LlamarApi.request("DELETE", "/pet/" + id)
        );
    }
}
