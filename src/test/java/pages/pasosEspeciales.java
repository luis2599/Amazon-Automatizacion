package pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class pasosEspeciales extends pasosBasicos {

    validaciones validacion = new validaciones();

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
                            "select.style.width = 'auto';");

            // Ahora podemos interactuar con el select
            WebElement select = driver.findElement(locator);
            Select dropdown = new Select(select);
            dropdown.selectByVisibleText(valor);
            System.out.println("Valor seleccionado por texto visible: " + valor);
            click(locator);

            // opción mas robusta para seleccionar opción, ya que en algunos casos el texto
            // visible puede no coincidir exactamente con el valor que se muestra en la
            // lista, por lo que se busca por texto visible y si no se encuentra, se busca
            // por value o index
            /*
             * // Intentar seleccionar por texto visible
             * try {
             * dropdown.selectByVisibleText(pais);
             * System.out.println("País seleccionado por texto visible: " + pais);
             * click(locator);
             * } catch (Exception e) {
             * // Si no funciona, buscar por value o index
             * List<WebElement> opciones = dropdown.getOptions();
             * for (int i = 0; i < opciones.size(); i++) {
             * if (opciones.get(i).getText().contains(pais)) {
             * dropdown.selectByIndex(i);
             * System.out.println("País seleccionado por índice: " + i);
             * break;
             * }
             * }
             * }
             * 
             * // Restaurar la visibilidad del select
             * js.executeScript(
             * "document.getElementById('GLUXCountryList').style.display = 'none';"
             * );
             */
        } catch (Exception e) {
            System.out.println("Error seleccionando valor: " + e.getMessage());
        }
    }

    public boolean tomarMuestrasDeResultados(By locator, String producto) {
        System.out.println("eh llegue aqui CV");

        // Se toma una lista de todos los productos encontrados en la pagina de
        // resultados
        List<WebElement> todosLosResultados = driver.findElements(locator);

        // se crean listas para cada categoria de resultados
        List<WebElement> patrocinados = new ArrayList<>();
        List<WebElement> organicos = new ArrayList<>();
        List<WebElement> masVendidos = new ArrayList<>();
        List<WebElement> enTendencia = new ArrayList<>();
        List<WebElement> seleccionGeneral = new ArrayList<>();

        for (WebElement productos : todosLosResultados) {
            String texto = productos.getText();

            if (texto.contains("Patrocinado")) {
                patrocinados.add(productos);
            }

            if (texto.contains("Más vendido") || texto.contains("Más venta")) {
                masVendidos.add(productos);
            }

            if (texto.contains("En tendencia")) {
                enTendencia.add(productos);
            }

            if (texto.contains("Selección general")) {
                seleccionGeneral.add(productos);
            }

            // Los que NO tienen "Patrocinado" son orgánicos
            if (!texto.contains("Patrocinado")) {
                organicos.add(productos);
            }
        }

        if (todosLosResultados.size() != 0) {
            // Validar que se hayan encontrado resultados en cada categoría
            if (!patrocinados.isEmpty()) { 
                obtenerTextoWebElement(patrocinados.get(0));
                Assert.assertTrue(obtenerTextoWebElement(patrocinados.get(0)).toLowerCase().contains(producto.toLowerCase()), "El producto no se encuentra en los resultados patrocinados");
                System.out.println("Nombre de producto \"patrocinados\" validado: ");
            }
            if (!organicos.isEmpty()) {
                obtenerTextoWebElement(organicos.get(0));
                Assert.assertTrue(obtenerTextoWebElement(organicos.get(0)).toLowerCase().contains(producto.toLowerCase()), "El producto no se encuentra en los resultados patrocinados");
                System.out.println("Nombre de producto \"organicos\" validado: ");
            }
            if (!masVendidos.isEmpty()) {
                obtenerTextoWebElement(masVendidos.get(0)); // Primer "Más vendido"
                Assert.assertTrue(obtenerTextoWebElement(masVendidos.get(0)).toLowerCase().contains(producto.toLowerCase()), "El producto no se encuentra en los resultados más vendidos");
                System.out.println("Nombre de producto \"más vendidos\" validado: ");
            }
            if (!enTendencia.isEmpty()) {
                obtenerTextoWebElement(enTendencia.get(0)); // Primer "En tendencia"
                Assert.assertTrue(obtenerTextoWebElement(enTendencia.get(0)).toLowerCase().contains(producto.toLowerCase()), "El producto no se encuentra en los resultados en tendencia");
                System.out.println("Nombre de producto \"en tendencia\" validado: ");
            }
            return true;
        }else{
            System.out.println("No se encontraron resultados para validar");
            return false;
        }
    }

}
