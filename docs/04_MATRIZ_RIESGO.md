# Fase J | Matriz de riesgo

| Cambio candidato              | Probabilidad de romper | Impacto si rompe | Riesgo | Cómo reducirlo                                                                                           |
| ----------------------------- | ---------------------- | ---------------- | ------ | -------------------------------------------------------------------------------------------------------- |
| Extraer clase de notificación | Media                  | Medio            | Medio  | Crear primero pruebas que verifiquen los mensajes que aparecen cuando una reserva válida es procesada.   |
| Introducir Correo             | Media                  | Medio            | Medio  | Crear pruebas para correo válido, correo inválido y correo `null` antes del cambio.                      |
| Introducir PeriodoReserva     | Alta                   | Alto             | Alto   | Crear pruebas para período válido, período inválido y límite de anticipación de 2 y 1 horas.             |
| Simplificar validaciones      | Media                  | Alto             | Alto   | Mantener pruebas para cada condición inválida y comprobar que los casos válidos continúan confirmándose. |
| Separar cálculo VIP           | Baja                   | Medio            | Bajo   | Crear pruebas para reservas NORMAL y VIP y verificar que los totales continúan siendo 40.0 y 34.0.       |

## Escala

* **Bajo:** cambio local, comportamiento bien entendido y prueba fácil de crear.
* **Medio:** afecta varias decisiones o requiere adaptar construcción de objetos.
* **Alto:** puede alterar contrato observable, flujos de error o efectos externos.
