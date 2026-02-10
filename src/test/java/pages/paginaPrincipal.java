package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class paginaPrincipal extends pasosBasicos {

    private By buscador = By.xpath("//input[@id='twotabsearchtextbox']");
    private String producto = "laptop";

    public paginaPrincipal() {
        super(driver);
    }

    public void navegar() {
        navigateTo("https://www.amazon.com/");
    }

    public void ingresarProducto() {
        click(buscador);
        escribirTexto(buscador, producto);
        enter(buscador);
    }
    
}
