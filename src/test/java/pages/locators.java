package pages;

import org.openqa.selenium.By;

public class locators {

    // locators utilizados en el archivo paginaPrincipal.java
    public static class paginaPrincipal {
        public static final By buscador = By.xpath("//input[@id='twotabsearchtextbox']");
        public static final By botonPais = By.xpath("//*[@id=\"nav-packard-glow-loc-icon\"]");
        public static final By modalPais = By.id("a-popover-1");
        public static final By opcionPais = By.id("GLUXCountryList");
        public static final By botonOkPais = By.xpath("//button[@name='glowDoneButton']");
        public static final By textoResultados = By.xpath("//span[@class='a-color-state a-text-bold']");
        public static final By cambioDireccion = By
                .xpath("//*[@id=\"nav-flyout-iss-anchor\"]/div[2]/div/div[3]/span[1]/span/input");
        public static final By seccionResultados = By.xpath("//*[@id=\"search\"]/div[1]");
        public static final By precio = By.xpath("//span[normalize-space()='3,660,336']");
        public static final By mensajeToaster = By.xpath("//div[@class='glow-toaster-content']");
    }

    // locators utilizados en el archivo filtros.java
    public static class filtros {
        public static final By contenedorFiltros = By.xpath("//div[contains(@id, 's-refinements')]");
    }

    // locators utilizados en el archivo resultados.java
    public static class resultados {
        public static final By textoResutado1 = By.xpath(
                "//*[@id=\"83a50ed6-1d27-41d9-9ff1-63d6aee3aa1c\"]/div/div/div/div/span/div/div/div/div[2]/div/div/div[1]/a");
        public static final By contenedorResultados = By
                .xpath("//div[contains(@data-component-type, 's-search-result')]");
        public static final By tituloProducto = By.xpath(".//div[@data-cy='title-recipe']//h2//span");
        public static final By tituloProductoDetalle = By.xpath("//div[contains(@id,\"title_feature_div\")]");
        public static final By precioProducto = By
                .xpath("//div[contains(@id,'corePriceDisplay_desktop_feature_div')]//span[@class='a-price-whole']");
        public static final By botonCompra = By.xpath("//input[@id='buy-now-button']");
        public static final By seccionInfoProducto = By.xpath("//div[contains(@id,'prodDetails')]");
        public static final By seccionInfoTodosProducto = By.xpath("//div[contains(@id,'productDetails_feature_div')]");
    }

}
