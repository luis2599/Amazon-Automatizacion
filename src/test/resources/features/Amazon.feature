Feature: Busqueda y validacion de resultados
Como usuario se requiere buscar productos en Amazon.
Validar que el buscador funciona correctamente.

    Background: Ingreso pagina web
    Given Abre el navegador e ingresa al sitio web
    And Selecciona el pais de residencia "Colombia"

    Scenario: Busqueda de un producto
    When Ingresar el producto a buscar "laptop"
    Then El buscador está disponible
    And Validar que se muestran resultados relacionados con "laptop"
    And la pagina de resultados cargo de manera exitosa