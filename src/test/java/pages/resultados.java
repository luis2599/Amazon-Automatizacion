package pages;

import org.openqa.selenium.By;

public class resultados extends pasosBasicos {

    pasosEspeciales espe = new pasosEspeciales();

    public resultados() {
        super(driver);
    }

    private By textoResutado1 = By.xpath("//*[@id=\"83a50ed6-1d27-41d9-9ff1-63d6aee3aa1c\"]/div/div/div/div/span/div/div/div/div[2]/div/div/div[1]/a");
    private By contenedorResultados = By.xpath("//div[contains(@data-component-type, 's-search-result')]"); 
    
    public String recuperarTexto() {
        obtenerTexto(textoResutado1);
        return obtenerTexto(textoResutado1);
    }

    public boolean validarResultados(String producto) {
        return espe.tomarMuestrasDeResultados(contenedorResultados, producto);
    }
}
