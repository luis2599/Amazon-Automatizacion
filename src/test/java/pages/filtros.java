package pages;

import org.openqa.selenium.By;

public class filtros extends pasosBasicos {

    pasosEspeciales espe = new pasosEspeciales();

    public filtros() {
        super(driver);
    }

    By contenedorFiltros = locators.filtros.contenedorFiltros;

    public void tomarFiltros(String filtro, String categoria) {
        espe.seleccionFiltro(contenedorFiltros, filtro, categoria);
    }

    public boolean validarFiltroAplicado(String filtro) {

        By filtroActivo = By.xpath("//div[contains(@id,'s-refinements')]//a[.//span[contains(@class,'a-color-base') and contains(text(),'"+ filtro + "')]]");
        esperar(2);
        // Se obtiene el atributo "aria-current" del filtro para verificar si está activo
        String estado = driver.findElement(filtroActivo).getAttribute("aria-current");
        logger.info("Estado del filtro '", filtro, "': ", estado);
        if (estado.equals("true")) {
            return true;
        } else {
            logger.error("El filtro '", filtro, "' no está activo.");
            return false;
        }
    }

}
