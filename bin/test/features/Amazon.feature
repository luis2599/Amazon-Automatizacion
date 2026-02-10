Feature: Busqueda y validacion de resultados
Como usuario se requiere buscar productos en Amazon.
Validar que el buscador funciona correctamente.

    Background: Ingreso pagina web
    Given Abre el navegador e ingresa al sitio web

    Scenario: Busqueda de un producto
    When Ingresar el producto a buscar
    ##Then Validar que el producto se encuentra en los resultados de busqueda