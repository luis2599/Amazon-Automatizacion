package steps;

import org.testng.asserts.SoftAssert;
import io.cucumber.java.en.*;
import pages.paginaPrincipal;
import pages.pasosBasicos;
import pages.resultados;

public class amazon {

    paginaPrincipal main = new paginaPrincipal();
    SoftAssert soft = new SoftAssert();
    resultados resul = new resultados();

    @Given("Abre el navegador e ingresa al sitio web")
    public void navegacionTo() {
        main.navegar();

    }

    @Given("^(?:Selecciona|El usuario Selecciona) el pais de residencia \"([^\"]*)\"$")
    public void seleccionarPais(String pais) { 
        main.seleccionarPais(pais);
    }

    //step para ingresar el producto a buscar, recibe un string (\"([^\"]*)\") como parametro desde el feature file
    //Se usa \"([^\"]*)\" para capturar el texto entre comillas, ya que al usar expresiones regulares, el genera conflicto el {String} con el formato de Cucumber, por lo que se opta por usar esta expresión regular para capturar el texto entre comillas.
    @When("^(?:Ingresar|El usuario ingresa) el producto a buscar \"([^\"]*)\"$")
    public void ingresarProducto(String producto) {
        main.ingresarProducto(producto);
    }

    @Then("^(?:El buscador|Buscador|Buscar en Amazon) está disponible$")
    public void validarBuscador() {
        soft.assertTrue(main.buscadorDisponible(), "El buscador no está disponible");
        soft.assertAll();
    }

    @Then("^(?:Validar que se muestran resultados relacionados con|Validar que se muestran resultados relacionados con el producto) \"([^\"]*)\"$")
    public void validarResultados(String producto) {
        soft.assertTrue(main.recuperarTexto().toLowerCase().contains(producto.toLowerCase()), "El producto no se encuentra en los resultados de búsqueda");
        soft.assertAll();
    }   
    
    @Then("^(?:la pagina de resultados cargo de manera exitosa|Validar que la pagina de resultados cargo de manera exitosa)$")
    public void validarCargaPaginaResultados() {      
        soft.assertTrue(main.seccionResultadosDisponible(), "La pagina de resultados no cargo de manera exitosa");
        soft.assertTrue(main.valorEsperado(), "No se encontraron resultados en la pagina de resultados");
        soft.assertTrue(main.valorEsperadoCantidad() > 0, "La cantidad de resultados encontrados es 0");
        soft.assertAll();
    }

    @Then("^(?:Existe un listado de resultados|Validar que existe un listado de resultados)$")
    public void validarListadoResultados() {
        soft.assertTrue(main.seccionResultadosDisponible(), "La pagina de resultados no cargo de manera exitosa");
        soft.assertAll();
    }

    @Then("^(?:Resultado contiene el texto buscado|Validar que el resultado contiene el texto buscado) \"([^\"]*)\"$")
    public void validarTextoResultado(String producto) {
        soft.assertTrue(resul.validarResultados(producto), "No se encontraron resultados que contengan el texto buscado");
        soft.assertAll();
    }

    /*@Then("^(?:Resultados contienen informacion minima|Validar que los resultados contienen informacion minima)$")
    public void validarInformacionMinimaResultados() {      
        resul.validarResultados();
    }*/
}
