package edu.uees.refactor.service;

import edu.uees.refactor.domain.Reserva;

public class ServicioReservas {

    public double procesar(
            Reserva r,
            int horasAnticipacion) {

        if (!esReservaValida(r, horasAnticipacion)) {
            return 0;
        }

        double total = calcularTotal(r);

        System.out.println(
                "Guardando reserva " + r.getId()
        );

        System.out.println(
                "Correo enviado a " + r.getCorreo()
        );

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

    private double calcularTotal(Reserva r) {
        double total = 40;

        if ("VIP".equals(r.getTipo())) {
            total = total * 0.85;
        }

        return total;
    }
}