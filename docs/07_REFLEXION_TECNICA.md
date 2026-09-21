# Reflexión técnica final

Antes de modificar una clase heredada que aparentemente funciona bien, considero importante entender qué hace y comprobar cómo se comporta actualmente. En este caso, `ServicioReservas` tenía varias responsabilidades, como validar los datos, calcular el precio, simular el guardado, enviar el correo y confirmar la reserva. Por eso, antes de hacer cambios fue necesario tener pruebas que permitieran comprobar que el comportamiento se mantuviera.

En Ae5 se realizaron tres refactorizaciones de forma incremental. Primero se separó la validación de la reserva en el método `esReservaValida()`, lo que permitió que `procesar()` quedara más fácil de leer. Después se movió el cálculo del total a la clase `Reserva`, porque ese cálculo está relacionado directamente con los datos de la reserva. Finalmente, se extrajo la notificación a la clase `ReservaNotificador`, dejando esa responsabilidad separada de la lógica principal del servicio.

Después de cada cambio se ejecutó `mvn clean test` y las 7 pruebas terminaron correctamente, sin fallos ni errores. Esto permitió hacer los cambios pequeños y comprobar inmediatamente que no se alteraran los casos que ya estaban protegidos por las pruebas.

También se pudo comprobar anteriormente que las pruebas funcionan como una red de seguridad. Al cambiar temporalmente el descuento VIP de `0.85` a `0.80`, la prueba correspondiente falló porque esperaba `34.0` y recibió `32.0`. Después de restaurar el comportamiento original, las pruebas volvieron a pasar.

Con esta experiencia considero que refactorizar no significa cambiar lo que hace el sistema. La idea es mejorar la estructura interna para que el código tenga responsabilidades más claras y sea más fácil de mantener, conservando el comportamiento que ya debía cumplir. Por eso, las pruebas y los commits pequeños fueron importantes durante todo el proceso.
