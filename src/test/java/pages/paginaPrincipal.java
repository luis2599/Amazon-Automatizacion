package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class paginaPrincipal extends pasosBasicos {

    pasosEspeciales espe = new pasosEspeciales();

    private By buscador = By.xpath("//input[@id='twotabsearchtextbox']");
    private By botonPais = By.xpath("//*[@id=\"nav-packard-glow-loc-icon\"]");
    private By modalPais = By.id("a-popover-1");
    private By opcionPais = By.id("GLUXCountryList");
    private By botonOkPais = By.xpath("//button[@name='glowDoneButton']");
    //private By titulo = By.xpath("//*[@id=\"a-popover-1\"]");
    //private String producto = "laptop";

    public paginaPrincipal() {
        super(driver);
    }

    public void navegar() {
        navigateTo("https://www.amazon.com/");
    }

    public void ingresarProducto(String producto) {
        click(buscador);
        escribirTexto(buscador, producto);
        enter(buscador);
    }

    public void seleccionarPais(String pais) {
        click(botonPais);
        cambiarAVentanaEmergente(modalPais);
        espe.seleccionarOpcionListaOculta(opcionPais, pais);
        click(botonOkPais);
        cambiarAVentanaPrincipal(modalPais);
    }
    
}
