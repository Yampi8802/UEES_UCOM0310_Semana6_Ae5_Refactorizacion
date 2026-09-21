# UEES | Diseño de Software | UCOM0310

## Semana 6 | Laboratorio evaluado 1

### Ae5 | Refactorización respaldada por pruebas unitarias

**Estudiante:** Jean Orozco
**Asignatura:** Diseño de Software
**Código:** UCOM0310
**Semana:** 6

---

## 1. Descripción del proyecto

Este proyecto parte de un código heredado de un sistema sencillo de reservas.

El objetivo de esta actividad fue primero comprender y registrar el comportamiento existente y después realizar refactorizaciones respaldadas por pruebas unitarias, buscando mejorar la estructura interna del código sin cambiar el comportamiento que debía mantenerse.

El trabajo se realizó de forma incremental, siguiendo el ciclo:

**PRUEBA VERDE → CAMBIO PEQUEÑO → PRUEBA VERDE → COMMIT → SIGUIENTE CAMBIO**

---

## 2. Tecnologías utilizadas

* Java 21
* Maven
* JUnit 5
* Git
* VS Code

---

## 3. Estructura del proyecto

```text
UEES_UCOM0310_Semana6_Lab1_Proyecto_BASE/
├── pom.xml
├── README.md
├── docs/
│   ├── 01_LINEA_BASE.md
│   ├── 02_MAPA_RESPONSABILIDADES.md
│   ├── 03_MATRIZ_DIAGNOSTICO.md
│   ├── 04_MATRIZ_RIESGO.md
│   ├── 05_PRUEBAS_PROPUESTAS.md
│   ├── 06_PLAN_REFACTORIZACION.md
│   └── 07_REFLEXION_TECNICA.md
└── src/
    ├── main/
    │   └── java/
    │       └── edu/
    │           └── uees/
    │               └── refactor/
    │                   ├── app/
    │                   │   └── Main.java
    │                   ├── domain/
    │                   │   ├── EstadoReserva.java
    │                   │   └── Reserva.java
    │                   └── service/
    │                       ├── ReservaNotificador.java
    │                       └── ServicioReservas.java
    └── test/
        └── java/
            └── edu/
                └── uees/
                    └── refactor/
                        └── service/
                            └── ServicioReservasTest.java
```

---

## 4. Diagnóstico inicial

Antes de modificar el diseño se realizó una línea base manual y un diagnóstico del código.

Se identificó que `ServicioReservas` concentraba varias responsabilidades:

* Validar la reserva.
* Revisar correo, período y anticipación.
* Calcular el total.
* Aplicar el descuento VIP.
* Simular el guardado de la reserva.
* Simular el envío del correo.
* Ordenar la confirmación de la reserva.

También se identificó dependencia de varios datos internos de `Reserva` y el uso de valores como `40` y `0.85` dentro de la lógica de cálculo.

La documentación del diagnóstico se encuentra en la carpeta `docs/`.

---

## 5. Red de seguridad con JUnit 5

Antes de realizar las refactorizaciones se construyó una red de seguridad mediante pruebas unitarias.

Actualmente existen **7 pruebas** en:

```text
src/test/java/edu/uees/refactor/service/ServicioReservasTest.java
```

Las pruebas cubren:

* Reserva NORMAL válida.
* Reserva VIP válida.
* Correo inválido.
* Período inválido.
* Exactamente 2 horas de anticipación.
* 1 hora de anticipación.
* Reserva nula.

Estas pruebas permiten comprobar que los resultados y estados principales se mantienen después de cada cambio.

---

## 6. Refactorizaciones realizadas

### Refactorización 1 | Separar la validación

Se extrajo la lógica de validación de `procesar()` a un método independiente:

```java
esReservaValida(...)
```

Esto permite separar la validación del resto del flujo de procesamiento y facilita la lectura del método principal.

**Commit:**

```text
2fc6002 refactor: separar validacion de reserva
```

Después del cambio:

```text
Tests run: 7
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
```

---

### Refactorización 2 | Move Method

El cálculo del total se encontraba relacionado directamente con los datos propios de `Reserva`, especialmente con su tipo.

Por eso, el método:

```java
calcularTotal()
```

se movió desde `ServicioReservas` hacia `Reserva`.

Ahora `ServicioReservas` solicita el cálculo a la propia reserva:

```java
double total = r.calcularTotal();
```

Esto mejora la responsabilidad de cada clase y reduce la dependencia del servicio sobre los detalles de la reserva.

**Commit:**

```text
8017c8e refactor: mover calculo de total a reserva
```

Después del cambio:

```text
Tests run: 7
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
```

---

### Refactorización 3 | Extract Class

La responsabilidad de enviar el correo estaba dentro de `ServicioReservas`.

Se creó la clase:

```text
ReservaNotificador
```

Esta clase contiene la responsabilidad de simular el envío del correo.

Ahora `ServicioReservas` utiliza:

```java
notificador.enviarCorreo(r);
```

Con esto se separa la responsabilidad de notificación del servicio principal.

**Commit:**

```text
021459b refactor: extraer notificacion de reserva
```

Después del cambio:

```text
Tests run: 7
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
```

---

## 7. Resultado final de las pruebas

Después de completar las tres refactorizaciones se ejecutó:

```bash
mvn clean test
```

Resultado final:

```text
Tests run: 7
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
```

Esto indica que las pruebas continuaron pasando después de los cambios realizados.

---

## 8. Evidencia de regresión

Como parte de la construcción de la red de seguridad se comprobó que una modificación funcional en el descuento VIP fuera detectada por las pruebas.

Al cambiar temporalmente el factor VIP de:

```text
0.85
```

a:

```text
0.80
```

la prueba correspondiente al total VIP falló.

El resultado obtenido fue:

```text
Expected: 34.0
Actual: 32.0
```

con:

```text
Tests run: 7
Failures: 1
Errors: 0
Skipped: 0
```

Después se restauró el comportamiento original y las pruebas volvieron a pasar.

Esta comprobación permitió verificar que la red de seguridad podía detectar un cambio que modificara el resultado esperado.

---

## 9. Historial Git

El trabajo fue realizado mediante cambios pequeños e incrementales.

Historial principal:

```text
021459b refactor: extraer notificacion de reserva
8017c8e refactor: mover calculo de total a reserva
2fc6002 refactor: separar validacion de reserva
b0475b6 refactor: extraer calculo de total
f89e055 test: caracterizar comportamiento heredado de reservas
256c6e7 chore: registrar proyecto heredado y linea base
```

La secuencia permite observar la evolución del proyecto desde la línea base, pasando por las pruebas, hasta las refactorizaciones realizadas.

---

## 10. Estado final del diseño

Al finalizar la actividad, las responsabilidades principales quedaron distribuidas de la siguiente manera:

### `ServicioReservas`

Se encarga principalmente de coordinar el procesamiento de la reserva:

* Validar la reserva.
* Calcular el total mediante `Reserva`.
* Simular el guardado.
* Solicitar la notificación.
* Confirmar la reserva.

### `Reserva`

Se encarga de información y comportamiento propio de la reserva:

* Datos de la reserva.
* Estado.
* Confirmación.
* Cálculo del total.

### `ReservaNotificador`

Se encarga de:

* Simular el envío del correo de confirmación.

Esta separación permite que cada clase tenga responsabilidades más claras que en la versión inicial.

---

## 11. Comportamiento conservado

Las refactorizaciones buscaron modificar la estructura interna sin cambiar los resultados que las pruebas protegen.

Entre los comportamientos conservados están:

* Una reserva NORMAL válida devuelve `40.0`.
* Una reserva VIP válida devuelve `34.0`.
* Una reserva válida queda en estado `CONFIRMADA`.
* Un correo inválido devuelve `0.0`.
* Un período inválido devuelve `0.0`.
* Una anticipación de 2 horas permite procesar la reserva.
* Una anticipación de 1 hora no permite procesarla.
* Una reserva nula devuelve `0.0`.

---

## 12. Comandos principales

### Compilar

```bash
mvn clean compile
```

### Ejecutar las pruebas

```bash
mvn clean test
```

### Ver historial Git

```bash
git log --oneline --decorate
```

### Ver estado del repositorio

```bash
git status
```

---

## 13. Documentación

La documentación del proceso se encuentra en:

```text
docs/
```

Incluye:

* Línea base.
* Mapa de responsabilidades.
* Matriz de diagnóstico.
* Matriz de riesgo.
* Pruebas propuestas.
* Plan de refactorización.
* Reflexión técnica.

---

## 14. Declaración de uso de IA

Para el desarrollo de esta actividad se utilizó inteligencia artificial como herramienta de apoyo para comprender instrucciones, revisar decisiones de refactorización, analizar resultados de pruebas y organizar la documentación.

Las decisiones sobre los cambios realizados, ejecución del proyecto, pruebas, commits y verificación final fueron realizadas y comprobadas en el entorno del proyecto.

---

## 15. Repositorio

**Repositorio GitHub:** 
https://github.com/Yampi8802/UEES_UCOM0310_Semana6_Ae5_Refactorizacion.git

