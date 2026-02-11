package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class pasosEspeciales extends pasosBasicos {

    public pasosEspeciales() {
        super(driver);
    }

    public void seleccionarOpcionListaOculta(By locator, String valor) {
        try {
        // Esperar a que el select esté presente
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        
        // Usar JavaScript para interactuar con el select oculto
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Obtener el ID del elemento desde el locator
        String id = locator.toString().replace("By.id: ", "");
        
        // Hacer visible temporalmente el select
        js.executeScript(
            "var select = document.getElementById('" + id + "');" +
            "select.style.display = 'block';" +
            "select.style.visibility = 'visible';" +
            "select.style.opacity = '1';" +
            "select.style.height = 'auto';" +
            "select.style.width = 'auto';"
        );
        
        // Ahora podemos interactuar con el select
        WebElement select = driver.findElement(locator);
        Select dropdown = new Select(select);
        dropdown.selectByVisibleText(valor);
        System.out.println("Valor seleccionado por texto visible: " + valor);
        click(locator);

        //opción mas robusta para seleccionar opción, ya que en algunos casos el texto visible puede no coincidir exactamente con el valor que se muestra en la lista, por lo que se busca por texto visible y si no se encuentra, se busca por value o index
        /* 
        // Intentar seleccionar por texto visible
        try {
            dropdown.selectByVisibleText(pais);
            System.out.println("País seleccionado por texto visible: " + pais);
            click(locator);
        } catch (Exception e) {
            // Si no funciona, buscar por value o index
            List<WebElement> opciones = dropdown.getOptions();
            for (int i = 0; i < opciones.size(); i++) {
                if (opciones.get(i).getText().contains(pais)) {
                    dropdown.selectByIndex(i);
                    System.out.println("País seleccionado por índice: " + i);
                    break;
                }
            }
        }
        
        // Restaurar la visibilidad del select
        js.executeScript(
            "document.getElementById('GLUXCountryList').style.display = 'none';"
        );*/
    } catch (Exception e) {
        System.out.println("Error seleccionando valor: " + e.getMessage());
    }
    }
    
}
