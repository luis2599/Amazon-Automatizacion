package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class filtros extends pasosBasicos {

    pasosEspeciales espe = new pasosEspeciales();

    public filtros() {
        super(driver);
    }  
    
    private By contenedorFiltros = By.xpath("//div[contains(@data-component-type, 's-filters-panel-view')]");

    public void tomarFiltros(String filtro, String categoria) {
        espe.seleccionFiltro(contenedorFiltros, filtro, categoria);
    }
    
}
