package steps;

import io.cucumber.java.en.*;
import pages.paginaPrincipal;

public class amazon {

    paginaPrincipal main = new paginaPrincipal();

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
    
}
