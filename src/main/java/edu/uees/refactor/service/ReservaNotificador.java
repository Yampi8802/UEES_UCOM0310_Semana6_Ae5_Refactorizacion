package edu.uees.refactor.service;

import edu.uees.refactor.domain.Reserva;

public class ReservaNotificador {

    public void enviarCorreo(Reserva r) {
        System.out.println(
                "Correo enviado a " + r.getCorreo()
        );
    }
}