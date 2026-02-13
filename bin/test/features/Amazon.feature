@Amazon
Feature: Busqueda y validacion de resultados
Como usuario se requiere buscar productos en Amazon.
Validar que el buscador funciona correctamente.

    Background: Ingreso pagina web
    Given Abre el navegador e ingresa al sitio web
    And Selecciona el pais de residencia "Colombia"

    @Busqueda
    Scenario: Busqueda de un producto
    When Ingresar el producto a buscar "laptop"
    Then El buscador está disponible
    And Validar que se muestran resultados relacionados con "laptop"
    And la pagina de resultados cargo de manera exitosa

    @ValidacionResultados
    Scenario: Validacion de resultados
    When Ingresar el producto a buscar "dron"
    Then Existe un listado de resultados
    And Resultado contiene el texto buscado "dron"
    #And Resultados contienen informacion minima

    @Filtros
    Scenario: Aplicacion de filtro
    When El usuario ingresa el producto a buscar "iphone"
    And El usuario aplica el filtro "Apple" en la categoria "Marcas"
    #Then Validar que se muestran resultados relacionados con "iphone" y "Apple"