# AUTO_API_PETSTORE_SCREENPLAY

## Escenarios cubiertos

| Escenario | Método HTTP | Endpoint |
|---|---|---|
| Carga de un nuevo Pet | `POST` | `/pet` |
| Obtener una mascota existente | `GET` | `/pet/{id}` |
| Actualizar nombre y estado de una mascota | `PUT` | `/pet/{id}` (form data) |
| Eliminar una mascota existente | `DELETE` | `/pet/{id}` |

---

## Ejecución de los tests

### Comando completo (limpia, ejecuta y genera reportes)

```bash
./gradlew clean test aggregate
```

En Windows:

```cmd
gradlew.bat clean test aggregate
```

### Solo ejecutar los tests

```bash
./gradlew test
```

### Ejecutar y generar todos los reportes Serenity

```bash
./gradlew clean test aggregate reports
```

> Los tres pasos (`test`, `aggregate`, `reports`) también se ejecutan automáticamente al correr el proyecto con la tarea por defecto, ya que `build.gradle` declara `defaultTasks 'clean', 'test', 'aggregate'`.

---

## Reportes

Después de la ejecución se generan los siguientes reportes:

| Reporte | Ruta |
|---|---|
| Reporte Serenity completo | `target/site/serenity/index.html` |
| Resumen HTML (single page) | `target/site/serenity/serenity-summary.html` |
| Resultados JUnit (XML) | `build/test-results/test/` |
| Reporte de tests Gradle | `build/reports/tests/test/index.html` |

Abrir el reporte principal:

```bash
# Windows
start target\site\serenity\index.html
```

## Estructura del proyecto

```
AUTO_API_PETSTORE_SCREENPLAY/
├── build.gradle                          # Configuración de dependencias y tareas Gradle
├── serenity.conf                         # Configuración de Serenity BDD
├── logback-test.xml                      # Configuración de logging
└── src/
    └── test/
        ├── java/
        │   └── com/petstore/
        │       ├── hooks/
        │       │   └── Hooks.java                    # Setup del Stage y RestAssured antes de cada escenario
        │       ├── questions/
        │       │   └── EstadoUltimaRespuesta.java    # Question: lee el status code de la última respuesta HTTP
        │       ├── runners/
        │       │   └── EjecutarFeatures.java         # Suite JUnit 5 + Cucumber
        │       ├── stepdefinitions/
        │       │   └── GestionDePetsStepDefinition.java  # Definiciones de pasos Gherkin
        │       ├── tasks/
        │       │   ├── LlamarApi.java                # Task genérica: ejecuta llamadas HTTP
        │       │   ├── CrearMascota.java             # Task: POST /pet
        │       │   ├── ActualizarMascota.java        # Task: PUT /pet
        │       │   ├── EliminarMascota.java          # Task: DELETE /pet/{id}
        │       │   ├── ObtenerMascotaPorId.java      # Task: GET /pet/{id}
        │       │   └── ListarMascotasPorEstado.java  # Task: GET /pet/findByStatus
        │       └── util/
        │           └── Constantes.java               # URL base y constantes globales
        └── resources/
            └── features/
                └── consulta.feature                  # Escenarios BDD del CRUD de mascotas
```

## Tecnologías

| Herramienta | Versión |
|---|---|
| Java | 17 |
| Gradle | 9.4.0 |
| Serenity BDD | 4.2.22 |
| Cucumber JUnit Platform Engine | 7.20.1 |
| JUnit 5 | 5.11.4 |
| RestAssured (via Serenity) | 4.2.22 |