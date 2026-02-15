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

    private By contenedorFiltros = By.xpath("//div[contains(@id, 's-refinements')]");

    public void tomarFiltros(String filtro, String categoria) {
        espe.seleccionFiltro(contenedorFiltros, filtro, categoria);
    }

    public boolean validarFiltroAplicado(String filtro) {

        By filtroActivo = By.xpath("//div[contains(@id,'s-refinements')]//a[.//span[contains(@class,'a-color-base') and contains(text(),'"+ filtro + "')]]");
        esperar(2);
        String estado = driver.findElement(filtroActivo).getAttribute("aria-current");
        System.out.println("Estado del filtro '" + filtro + "': " + estado);
        if (estado.equals("true")) {
            return true;
        } else {
            System.out.println("El filtro '" + filtro + "' no está activo.");
            return false;
        }
    }

}
