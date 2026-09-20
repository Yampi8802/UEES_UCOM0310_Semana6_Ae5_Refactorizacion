# Fase L | Plan priorizado de refactorización

No implementes todavía.

| Orden | Cambio   | Por qué primero /después | Pruebas requeridas | Dependencias |
| ----: | --------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- | ----------------------------------------------------- |
|     1 | Extraer cálculo VIP                                 | Es una parte pequeña y bien delimitada del método. Permite reducir una responsabilidad sin modificar las reglas actuales de tarifa.                                                               | `vipConservaResultadoActual()`, `normalValidaConservaResultadoActual()`                                     | Línea base manual                                     |
|     2 | Simplificar validaciones                            | Las validaciones están concentradas al inicio de `procesar()` y pueden reorganizarse manteniendo las mismas condiciones y retornos.                                                               | `unaHoraNoPermiteProcesar()`, `periodoInvalidoNoProcesa()`, `correoValidoProcesaYCorreoInvalidoNoProcesa()` | Pruebas de validación                                 |
|     3 | Separar notificación y persistencia                 | `ServicioReservas` actualmente simula estas responsabilidades mediante `System.out.println`. Separarlas puede reducir el acoplamiento, pero debe protegerse primero el comportamiento observable. | `reservaValidaSeConfirma()` y pruebas de los mensajes actuales                                              | Pruebas de reserva válida                             |
|     4 | Introducir objetos de dominio para correo y período | Es un cambio más amplio porque modifica la forma en que se representan los datos y puede afectar la construcción de `Reserva`. Por eso debe hacerse después de estabilizar las reglas actuales.   | `correoValidoProcesaYCorreoInvalidoNoProcesa()`, `periodoInvalidoNoProcesa()` y pruebas de anticipación     | Pruebas de validación y comprensión del modelo actual |

## Ejemplo de razonamiento

1. Caracterizar los casos actuales mediante la línea base manual.
2. Realizar primero cambios pequeños y localizados, como separar el cálculo VIP.
3. Reorganizar las validaciones manteniendo exactamente las condiciones actuales.
4. Separar las responsabilidades de notificación y persistencia después de proteger su comportamiento observable.
5. Introducir nuevas representaciones para correo y período al final, porque requieren cambios más amplios en el modelo.

La evaluación se centra en **la justificación**, no en repetir exactamente este orden.
