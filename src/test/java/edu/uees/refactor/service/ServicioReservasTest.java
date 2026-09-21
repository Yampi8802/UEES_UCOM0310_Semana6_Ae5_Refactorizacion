package edu.uees.refactor.service;

import edu.uees.refactor.domain.EstadoReserva;
import edu.uees.refactor.domain.Reserva;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioReservasTest {

    private final ServicioReservas servicio = new ServicioReservas();

    @Test
    void debeProcesarReservaNormalValida() {
        // Arrange
        Reserva reserva = crearReserva("NORMAL");
        
        // Act
        double total = servicio.procesar(reserva, 3);

        // Assert
        assertEquals(40.0, total);
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
    }

    @Test
    void debeProcesarReservaVipValida() {
        // Arrange
        Reserva reserva = crearReserva("VIP");

        // Act
        double total = servicio.procesar(reserva, 3);

        // Assert
        assertEquals(34.0, total);
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
    }

    @Test
    void debeRechazarCorreoInvalido() {
        // Arrange
        Reserva reserva = new Reserva(
                "R003",
                "correo-invalido",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2),
                "NORMAL"
        );

        // Act
        double total = servicio.procesar(reserva, 3);

        // Assert
        assertEquals(0.0, total);
        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
    }

    @Test
    void debeRechazarPeriodoInvalido() {
        // Arrange
        Reserva reserva = new Reserva(
                "R004",
                "cliente@correo.com",
                LocalDateTime.now().plusHours(3),
                LocalDateTime.now().plusHours(2),
                "NORMAL"
        );

        // Act
        double total = servicio.procesar(reserva, 3);

        // Assert
        assertEquals(0.0, total);
        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
    }

    @Test
    void debeProcesarConExactamenteDosHorasDeAnticipacion() {
        // Arrange
        Reserva reserva = crearReserva("NORMAL");

        // Act
        double total = servicio.procesar(reserva, 2);

        // Assert
        assertEquals(40.0, total);
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
    }

    @Test
    void debeRechazarConUnaHoraDeAnticipacion() {
        // Arrange
        Reserva reserva = crearReserva("NORMAL");

        // Act
        double total = servicio.procesar(reserva, 1);

        // Assert
        assertEquals(0.0, total);
        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
    }

    @Test
    void debeRechazarReservaNula() {
        // Arrange
        Reserva reserva = null;

        // Act
        double total = servicio.procesar(reserva, 3);

        // Assert
        assertEquals(0.0, total);
    }

    private Reserva crearReserva(String tipo) {
        return new Reserva(
                "R001",
                "cliente@correo.com",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2),
                tipo
        );
    }
}