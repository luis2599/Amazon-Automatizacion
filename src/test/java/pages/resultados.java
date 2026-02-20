package pages;

import org.openqa.selenium.By;

public class resultados extends pasosBasicos {

    pasosEspeciales espe = new pasosEspeciales();

    public resultados() {
        super(driver);
    }

    //Se definen los localizadores para los elementos de la página de resultados, incluyendo un resultado específico y el contenedor general de resultados.
    By textoResutado1 = locators.resultados.textoResutado1;
    By contenedorResultados = locators.resultados.contenedorResultados; 
    By tituloProducto = locators.resultados.tituloProducto;
    By tituloProductoDetalle = locators.resultados.tituloProductoDetalle;
    By precioProducto = locators.resultados.precioProducto;
    By botonCompra = locators.resultados.botonCompra;
    By seccionInfoProducto = locators.resultados.seccionInfoProducto;
    By seccionInfoTodosProducto = locators.resultados.seccionInfoTodosProducto;

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
            logger.info("El precio del producto está disponible.");
            return true;
        } else {
            logger.error("El precio del producto no está disponible.");
            return false;
        }
    }

    //Se define un método para validar que el botón de compra está disponible en la página de detalles del producto, utilizando un método de la clase pasosEspeciales para realizar la validación.
    public boolean validarBotonCompra() {
        boolean respuesta = encontrar(botonCompra).isDisplayed();
        if (respuesta == true) {
            logger.info("El botón de compra está disponible.");
            return true;
        } else {
            logger.error("El botón de compra no está disponible.");
            return false;
        }
    }

    //Se define un método para validar que el título del producto está disponible en la página de detalles del producto, utilizando un método de la clase pasosEspeciales para realizar la validación.
    public boolean ValidarTituloProducto() {
        boolean respuesta = encontrar(tituloProductoDetalle).isDisplayed();
        if (respuesta == true) {
            logger.info("El título del producto está disponible.");
            return true;
        } else {
            logger.error("El título del producto no está disponible.");
            return false;
        }
    }

    //Se define un método para validar que la sección de información del producto está disponible en la página de detalles del producto, utilizando un método de la clase pasosEspeciales para realizar la validación.
    //Se utiliza el método scrollHaciaElemento para desplazarse hasta la sección de información del producto antes de verificar su disponibilidad, lo que mejora la precisión de la validación.
    public boolean validarSeccionInfoProducto() {
        scrollHaciaElemento(seccionInfoTodosProducto);
        boolean respuesta = encontrar(seccionInfoProducto).isDisplayed();
        if (respuesta == true) {
            logger.info("La sección de información del producto está disponible.");
            return true;
        } else {
            logger.error("La sección de información del producto no está disponible.");
            return false;
        }
    }
}