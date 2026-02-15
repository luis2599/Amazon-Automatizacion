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

    private List<WebElement> todosLosResultados;
    private List<WebElement> elementos;
    private List<WebElement> patrocinados;
    private List<WebElement> organicos;
    private List<WebElement> masVendidos;
    private List<WebElement> enTendencia;
    private List<WebElement> seleccionGeneral;
    private List<WebElement> todosLosFiltros;
    private List<WebElement> categoria;
    private List<WebElement> filtro;

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

    public void tomarMuestrasDeResultados(By locator) {
        // Se toma una lista de todos los productos encontrados en la pagina de
        todosLosResultados = driver.findElements(locator);
        patrocinados = new ArrayList<>();
        organicos = new ArrayList<>();
        masVendidos = new ArrayList<>();
        enTendencia = new ArrayList<>();
        seleccionGeneral = new ArrayList<>();

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

        System.out.println("Se han tomado todas las muestras de los resultados de búsqueda: " + todosLosResultados.size());

    }

    public boolean validarResultadosXNombre(By locator, String producto) {

        // Se llama al metodo tomarMuestrasDeResultados para obtener las listas de resultados de cada categoria, se le pasa el locator de los resultados y el producto buscado para validar que los resultados encontrados son relacionados al producto buscado
        tomarMuestrasDeResultados(locator);

        if (todosLosResultados != null && !todosLosResultados.isEmpty()) {
            // Validar que se hayan encontrado resultados en cada categoría
            if (!patrocinados.isEmpty()) { 
                obtenerTextoWebElement(patrocinados.get(0));
                //se valida que el primer resultado de cada categoria contenga el texto del producto buscado, esto para validar que los resultados encontrados son relacionados al producto buscado
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

    public void busquedaDeFiltro(By locator, String filtro, String titulocategoria) {
        // Se toma una lista de todos los filtros disponibles en la pagina de resultados
        todosLosFiltros = driver.findElements(locator);
        categoria = new ArrayList<>();
        for (WebElement categorias : todosLosFiltros) {
            String texto = categorias.getText();
            if (texto.contains(titulocategoria)) {
                System.out.println("Categoria encontrada: " + titulocategoria);
                esperar(10);
                //Se crea una variable By para asignarle de ubicación donde esta checkbox del filtro a seleccionar
                By checkCategoria = By.xpath("//div[contains(@id,'s-refinements')]//a[.//span[contains(@class,'a-color-base') and contains(text(),'"+filtro+"')]]");
                //Se busca en la página todos los elementos que coincidan con el XPath
                elementos = driver.findElements(checkCategoria);
                categoria.addAll(elementos);
                break; // Salir del bucle una vez que se encuentra la categoría
            }else{
                System.out.println("Categoria no encontrada: " + titulocategoria);
            }
        }
    }


    public void seleccionFiltro(By locator, String filtro, String titulocategoria) {
        
        busquedaDeFiltro(locator, filtro, titulocategoria);
        System.out.println(categoria.get(0).getText());
        if (!categoria.isEmpty()) {
            // Usar JavaScript para hacer scroll hasta el elemento y luego hacer clic
            ((JavascriptExecutor) pasosBasicos.driver).executeScript(
            "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
            categoria.get(0)
            );
            esperar(3);
            categoria.get(0).click();
        } else {
            System.out.println("No se encontraron categorías para seleccionar el filtro.");
        }
        
    }

}
