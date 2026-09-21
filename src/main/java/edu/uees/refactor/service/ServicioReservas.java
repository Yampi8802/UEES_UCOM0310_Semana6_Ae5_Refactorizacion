package edu.uees.refactor.service;

import edu.uees.refactor.domain.Reserva;

public class ServicioReservas {

    private final ReservaNotificador notificador;

    public ServicioReservas() {
        this.notificador = new ReservaNotificador();
    }

    public double procesar(
            Reserva r,
            int horasAnticipacion) {

        if (!esReservaValida(r, horasAnticipacion)) {
            return 0;
        }

        double total = r.calcularTotal();

        System.out.println(
                "Guardando reserva " + r.getId()
        );

        notificador.enviarCorreo(r);

        r.confirmar();

        return total;
    }

    private boolean esReservaValida(
            Reserva r,
            int horasAnticipacion) {

        if (r == null) {
            return false;
        }

        if (r.getCorreo() == null
                || !r.getCorreo().contains("@")) {
            return false;
        }

        if (r.getInicio() == null
                || r.getFin() == null
                || !r.getFin().isAfter(r.getInicio())) {
            return false;
        }

        if (horasAnticipacion < 2) {
            return false;
        }

        return true;
    }
}