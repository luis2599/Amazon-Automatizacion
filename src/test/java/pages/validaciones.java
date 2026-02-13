package pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import dev.failsafe.internal.util.Assert;
import io.cucumber.java.bs.A.As;

public class validaciones extends pasosBasicos {

    SoftAssert soft = new SoftAssert();

    public validaciones() {
        super(driver);
    }

    public boolean validarContieneImagen(WebElement locator) {
        System.out.println("eh llegue aqui");
        try {
            WebElement imagen = locator.findElement(By.xpath(".//img"));
            String src = imagen.getAttribute("src");
            String alt = imagen.getAttribute("alt");

            soft.assertTrue(src != null && src.startsWith("http"),
                    "URL de imagen inválida");
            soft.assertTrue(alt != null && alt.length() > 3,
                    "Texto alternativo de imagen muy corto");
            return true;
        } catch (NoSuchElementException e) {
            soft.assertTrue(false, "Producto sin imagen"); // No fatal, se reporta
            return false;
        }
    }

    public boolean validarContieneTitulo(WebElement locator) {
        try {
            WebElement titulo = locator.findElement(By.xpath(".//h2 | .//a[contains(@class, 'title')]"));            String textoTitulo = titulo.getText();
            soft.assertTrue(textoTitulo != null && textoTitulo.length() > 5,
                    "Título del producto es muy corto o nulo");
            return true;
        } catch (NoSuchElementException e) {
            soft.assertTrue(false, "Producto sin título");
            return false; // No fatal, se reporta
        }
    }   

}
