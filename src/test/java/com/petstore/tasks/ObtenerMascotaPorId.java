package com.petstore.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class ObtenerMascotaPorId implements Task {
    private final String id;

    public ObtenerMascotaPorId(String id) {
        this.id = id;
    }

    public static ObtenerMascotaPorId conId(String id) {
        return Tasks.instrumented(ObtenerMascotaPorId.class, id);
    }

    @Override
    public void performAs(Actor actor) {
        actor.attemptsTo(
                LlamarApi.request("GET", "/pet/" + id)
        );
    }
}
