# Fase D | Mapa actual de responsabilidades

| Fragmento                                | Responsabilidad observada   | Clase actual     |
| ---------------------------------------- | --------------------------- | ---------------- |
| Validar null/correo/periodo/anticipación | Validación de la reserva    | ServicioReservas |
| Calcular total y descuento VIP           | Cálculo de tarifa           | ServicioReservas |
| Imprimir "Guardando reserva"             | Persistencia simulada       | ServicioReservas |
| Imprimir "Correo enviado"                | Notificación simulada       | ServicioReservas |
| Cambiar estado a CONFIRMADA              | Cambio de estado de dominio | Reserva          |

## Mapa conceptual

```text
ServicioReservas
├── valida entrada
├── interpreta correo
├── interpreta periodo
├── decide anticipación
├── calcula precio
├── conoce descuento VIP
├── simula persistencia
├── simula notificación
└── ordena confirmar Reserva

Reserva
└── mantiene estado
```

**Pregunta clave:** ¿cuántas razones diferentes podría tener `ServicioReservas` para cambiar?

`ServicioReservas` podría tener varias razones para cambiar, porque actualmente concentra la validación de datos, el cálculo de tarifas, la persistencia simulada, la notificación y el procesamiento de la reserva. Un cambio en cualquiera de estas responsabilidades podría requerir modificar esta clase.
