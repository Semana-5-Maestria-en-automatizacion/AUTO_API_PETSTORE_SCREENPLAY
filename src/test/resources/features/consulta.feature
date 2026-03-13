Feature: Gestión de Pets
  Como usuario del sistema
  Quiero poder consultar y modificar informacion
  Para gestionar todo el CRUD del sistema de pets

  Scenario Outline: Carga de un nuevo Pet
    Given el servicio Petstore está disponible
    And el body contiene una mascota válida
    When el usuario envía POST /pet
    Then el sistema devuelve código <codigo>

    Examples:
      | codigo |
      | 200    |

  Scenario Outline: Obtener una mascota existente
    Given el servicio Petstore está disponible
    And existe una mascota con id <id>
    When el usuario envía GET /pet/<id>
    Then el sistema devuelve código <codigo>
    And la respuesta contiene los datos de la mascota

    Examples:
      | id | codigo |
      | 1  | 200    |

  Scenario Outline: Actualizar nombre y estado de una mascota
    Given el servicio Petstore está disponible
    And existe una mascota con id <id>
    When el usuario envía PUT /pet y el pet con id <id> existe
    Then el sistema devuelve código <codigo>

    Examples:
        | id | codigo |
        | 1  | 200    |

  Scenario Outline: Eliminar una mascota existente
    Given el servicio Petstore está disponible
    And existe una mascota con id <id>
    When el usuario envía DELETE /pet/<id>
    Then el sistema devuelve código <codigo>
    And la mascota deja de existir en el sistema

    Examples:
      | id | codigo |
      | 1  | 200    |