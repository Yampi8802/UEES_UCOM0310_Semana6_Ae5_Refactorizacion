# Fase K | Proponer pruebas antes de proponer código

| Refactorización candidata            | Comportamiento a proteger                                              | Prueba propuesta                                |
| ------------------------------------ | ---------------------------------------------------------------------- | ----------------------------------------------- |
| Extraer cálculo VIP                  | VIP conserva el resultado actual de 34.0                               | `vipConservaResultadoActual()`                  |
| Simplificar validación               | 1h retorna 0 y no confirma                                             | `unaHoraNoPermiteProcesar()`                    |
| Separar notificación                 | Reserva válida sigue confirmándose y mostrando los mensajes actuales   | `reservaValidaSeConfirma()`                     |
| Introducir periodo                   | Periodo inválido sigue rechazándose y retorna 0                        | `periodoInvalidoNoProcesa()`                    |
| Introducir correo                    | Correo válido permite procesar y correo inválido retorna 0             | `correoValidoProcesaYCorreoInvalidoNoProcesa()` |
| Separar responsabilidades            | Una reserva NORMAL válida mantiene el total de 40.0 y queda CONFIRMADA | `normalValidaConservaResultadoActual()`         |
| Mejorar representación del resultado | Los casos actualmente no procesables mantienen retorno 0               | `entradaNoProcesableConservaRetornoCero()`      |

> Primero define qué comportamiento necesitas proteger; después decide cómo reorganizar la estructura.

## Evidencia de la línea base

Los comportamientos anteriores fueron comprobados manualmente mediante los seis escenarios de línea base antes de realizar cualquier refactorización.
