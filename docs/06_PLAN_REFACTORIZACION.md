# Fase L | Plan priorizado de refactorización

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
## Resultado de la ejecución en Ae5

El plan anterior correspondía a la etapa de diagnóstico y sirvió como referencia para decidir los cambios. Durante la ejecución de Ae5 se priorizaron tres refactorizaciones que podían aplicarse de forma incremental y verificarse con las pruebas unitarias existentes.

Las refactorizaciones realizadas fueron:

| Orden | Refactorización realizada | Commit | Resultado |
| ----: | ------------------------------------------------ | ------- | --------------------------------------------- |
| 1 | Separar la validación de la reserva | `2fc6002` | `mvn clean test` exitoso: 7 pruebas, 0 fallos |
| 2 | Mover el cálculo del total a `Reserva` | `8017c8e` | `mvn clean test` exitoso: 7 pruebas, 0 fallos |
| 3 | Extraer la notificación de reserva | `021459b` | `mvn clean test` exitoso: 7 pruebas, 0 fallos |

Cada cambio se realizó de forma incremental siguiendo el ciclo de prueba verde, cambio pequeño, nueva prueba verde y commit. Las tres refactorizaciones conservaron el comportamiento protegido por las pruebas unitarias.