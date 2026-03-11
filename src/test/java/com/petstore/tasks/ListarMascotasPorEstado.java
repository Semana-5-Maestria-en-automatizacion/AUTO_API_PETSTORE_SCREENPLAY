package com.petstore.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class ListarMascotasPorEstado implements Task {
    private final String estado;

    public ListarMascotasPorEstado(String estado) {
        this.estado = estado;
    }

    public static ListarMascotasPorEstado conEstado(String estado) {
        return Tasks.instrumented(ListarMascotasPorEstado.class, estado);
    }

    @Override
    public void performAs(Actor actor) {
        String endpoint = "/pet/findByStatus?status=" + estado;
        actor.attemptsTo(
                LlamarApi.request("GET", endpoint)
        );
    }
}
