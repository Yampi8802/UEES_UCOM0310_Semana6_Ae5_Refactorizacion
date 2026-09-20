# Reflexión técnica final

Antes de modificar una clase heredada que aparentemente funciona bien, primero considero que hay que entender qué hace y comprobar cómo se comporta actualmente. En este caso, el problema que considero que genera mayor riesgo es que `ServicioReservas` tiene varias responsabilidades. La misma clase se encarga de validar los datos, calcular el precio, simular la persistencia, simular el envío del correo y confirmar la reserva. Por eso, cambiar una de estas partes podría terminar afectando otra sin darnos cuenta.

Un problema que parece fácil de corregir es simplificar las validaciones. A simple vista solo sería cuestión de ordenar o separar los `if`, pero al cambiar alguna condición podríamos hacer que una reserva que antes era válida ya no se procese, o que ocurra lo contrario. Por eso, primero hay que revisar los casos límite, especialmente las 2 horas de anticipación, que sí son válidas, y 1 hora, que no lo es.

Antes de modificar el código considero necesarias las pruebas de reservas NORMAL y VIP, correo válido e inválido, período válido e inválido y los límites de 2 y 1 hora. Estas pruebas ayudan a comprobar que el comportamiento actual se mantenga después de cada cambio.

La primera responsabilidad que movería sería el cálculo de la tarifa, porque está bien definido y es relativamente fácil comprobar que una reserva NORMAL sigue dando 40.0 y una VIP 34.0. La evidencia para justificar esta decisión sería la línea base obtenida de los seis escenarios y las pruebas propuestas.

Refactorizar no significa cambiar lo que hace el sistema. La refactorización busca mejorar la estructura interna del código manteniendo su comportamiento. En cambio, un cambio funcional modifica las reglas o los resultados que recibe el usuario. Por eso, antes de refactorizar es importante tener evidencia de cómo funciona actualmente el sistema, para poder comprobar que se cambió la estructura, pero se mantiene la funcionalidad que ya debía cumplir.
