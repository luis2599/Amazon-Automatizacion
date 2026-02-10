package steps;

import io.cucumber.java.en.*;
import pages.paginaPrincipal;

public class amazon {

    paginaPrincipal main = new paginaPrincipal();

    @Given("Abre el navegador e ingresa al sitio web")
    public void navegacionTo() {
        main.navegar();
    }

    @When("Ingresar el producto a buscar")
    public void ingresarProducto() {
        main.ingresarProducto();
    }
    
}
