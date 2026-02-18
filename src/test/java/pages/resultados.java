package pages;

import org.openqa.selenium.By;

public class resultados extends pasosBasicos {

    pasosEspeciales espe = new pasosEspeciales();

    public resultados() {
        super(driver);
    }

    //Se definen los localizadores para los elementos de la página de resultados, incluyendo un resultado específico y el contenedor general de resultados.
    private By textoResutado1 = By.xpath("//*[@id=\"83a50ed6-1d27-41d9-9ff1-63d6aee3aa1c\"]/div/div/div/div/span/div/div/div/div[2]/div/div/div[1]/a");
    private By contenedorResultados = By.xpath("//div[contains(@data-component-type, 's-search-result')]"); 
    private By tituloProducto = By.xpath(".//div[@data-cy='title-recipe']//h2//span");
    private By tituloProductoDetalle = By.xpath("//div[contains(@id,\"title_feature_div\")]");
    private By precioProducto = By.xpath("//div[contains(@id,'corePriceDisplay_desktop_feature_div')]//span[@class='a-price-whole']");
    private By botonCompra = By.xpath("//input[@id='buy-now-button']");
    private By seccionInfoProducto = By.xpath("//div[contains(@id,'prodDetails')]");
    private By seccionInfoTodosProducto = By.xpath("//div[contains(@id,'productDetails_feature_div')]");

    //Se define un método para recuperar el texto de un resultado específico, utilizando el localizador definido anteriormente.
    public String recuperarTexto() {
        obtenerTexto(textoResutado1);
        return obtenerTexto(textoResutado1);
    }
    
    //Se define un método para validar que los resultados de búsqueda contienen el nombre del producto buscado, utilizando un método de la clase pasosEspeciales para realizar la validación.
    public boolean validarResultados(String producto) {
        return espe.validarResultadosXNombre(contenedorResultados, producto);
    }

    //Se define un método para seleccionar un resultado específico de la página de resultados, utilizando un método de la clase pasosEspeciales para realizar la selección.
    public void seleccionarResultado() {
        espe.seleccionarArticulo(contenedorResultados, tituloProducto); 
    }

    //Se define un método para validar que el precio del producto está disponible en la página de detalles del producto y este contenga un valor real, utilizando un método de la clase pasosEspeciales para realizar la validación.
    public boolean validarPrecioProducto() {
        boolean respuesta = espe.validarPrecioProductoSeleccionado(precioProducto);
        if (respuesta == true) {
            System.out.println("El precio del producto está disponible.");
            return true;
        } else {
            System.out.println("El precio del producto no está disponible.");
            return false;
        }
    }

    //Se define un método para validar que el botón de compra está disponible en la página de detalles del producto, utilizando un método de la clase pasosEspeciales para realizar la validación.
    public boolean validarBotonCompra() {
        boolean respuesta = encontrar(botonCompra).isDisplayed();
        if (respuesta == true) {
            System.out.println("El botón de compra está disponible.");
            return true;
        } else {
            System.out.println("El botón de compra no está disponible.");
            return false;
        }
    }

    //Se define un método para validar que el título del producto está disponible en la página de detalles del producto, utilizando un método de la clase pasosEspeciales para realizar la validación.
    public boolean ValidarTituloProducto() {
        boolean respuesta = encontrar(tituloProductoDetalle).isDisplayed();
        if (respuesta == true) {
            System.out.println("El título del producto está disponible.");
            return true;
        } else {
            System.out.println("El título del producto no está disponible.");
            return false;
        }
    }

    //Se define un método para validar que la sección de información del producto está disponible en la página de detalles del producto, utilizando un método de la clase pasosEspeciales para realizar la validación.
    //Se utiliza el método scrollHaciaElemento para desplazarse hasta la sección de información del producto antes de verificar su disponibilidad, lo que mejora la precisión de la validación.
    public boolean validarSeccionInfoProducto() {
        scrollHaciaElemento(seccionInfoTodosProducto);
        boolean respuesta = encontrar(seccionInfoProducto).isDisplayed();
        if (respuesta == true) {
            System.out.println("La sección de información del producto está disponible.");
            return true;
        } else {
            System.out.println("La sección de información del producto no está disponible.");
            return false;
        }
    }
}