# Fase C | Línea base manual

Completa los seis escenarios **sin refactorizar el diseño**.

| ID    | Escenario        | Entrada principal         | Estado     | Retorno | Mensajes / excepción                                                                     |
| ----- | ---------------- | ------------------------- | ---------- | ------- | ---------------------------------------------------------------------------------------- |
| LB-01 | NORMAL válida    | NORMAL, correo válido, 5h | CONFIRMADA | 40.0    | "Guardando reserva R-001" y "Correo enviado a [ana@uees.edu.ec](mailto:ana@uees.edu.ec)" |
| LB-02 | VIP válida       | VIP, correo válido, 5h    | CONFIRMADA | 34.0    | "Guardando reserva R-001" y "Correo enviado a [ana@uees.edu.ec](mailto:ana@uees.edu.ec)" |
| LB-03 | Correo inválido  | "incorrecto"              | PENDIENTE  | 0.0     | No aparecen mensajes de guardado ni correo. No se produce excepción visible              |
| LB-04 | Periodo inválido | fin <= inicio             | PENDIENTE  | 0.0     | No aparecen mensajes de guardado ni correo. No se produce excepción visible              |
| LB-05 | Límite válido    | 2h anticipación           | CONFIRMADA | 34.0    | "Guardando reserva R-001" y "Correo enviado a [ana@uees.edu.ec](mailto:ana@uees.edu.ec)" |
| LB-06 | Límite inválido  | 1h anticipación           | PENDIENTE  | 0.0     | No aparecen mensajes de guardado ni correo. No se produce excepción visible              |

## Preguntas

1. ¿Qué valores cambian entre NORMAL y VIP?

Cambia el tipo de reserva y el total. La reserva NORMAL tiene un total de 40.0, mientras que la VIP tiene un descuento del 15% y queda en 34.0.

2. ¿Qué casos dejan la reserva en PENDIENTE?

Los casos de correo inválido, periodo inválido y anticipación de 1 hora dejan la reserva en PENDIENTE.

3. ¿Qué devuelve `procesar()` cuando una entrada no es procesable?

Devuelve `0.0`.

4. ¿Existe alguna excepción visible en el flujo actual?

No. En los seis escenarios probados no se mostró ninguna excepción visible.

5. ¿Qué mensajes aparecen solo cuando la reserva se confirma?

Aparecen los mensajes:

* "Guardando reserva R-001"
* "Correo enviado a [ana@uees.edu.ec](mailto:ana@uees.edu.ec)"
