package pages;

import org.openqa.selenium.By;


public class paginaPrincipal extends pasosBasicos {

    pasosEspeciales espe = new pasosEspeciales();

    //Se heredan los locators definidos en la pagina locators.java
    By buscador = locators.paginaPrincipal.buscador;
    private By botonPais = locators.paginaPrincipal.botonPais;
    private By modalPais = locators.paginaPrincipal.modalPais;
    private By opcionPais = locators.paginaPrincipal.opcionPais;
    private By botonOkPais = locators.paginaPrincipal.botonOkPais;
    private By textoResultados = locators.paginaPrincipal.textoResultados;
    private By cambioDireccion = locators.paginaPrincipal.cambioDireccion;
    private By seccionResultados = locators.paginaPrincipal.seccionResultados;
    private By precio = locators.paginaPrincipal.precio;
    private By mensajeToaster = locators.paginaPrincipal.mensajeToaster;

    public paginaPrincipal() {
        super(driver);
    }

    public void navegar() {
        refrescarPagina();
        navigateTo("https://www.amazon.com/");
        esperarCargaCompletaPagina();
    }

    public void ingresarProducto(String producto) {
        click(buscador);
        escribirTexto(buscador, producto);
        logger.info("Se escibio ", producto, " en el buscador");
        enter(buscador);
    }

    public void seleccionarPais(String pais) {
        click(botonPais);
        cambiarAVentanaEmergente(modalPais);
        esperarCargaCompletaPagina();
        espe.seleccionarOpcionListaOculta(opcionPais, pais);
        click(botonOkPais);
        cambiarAVentanaPrincipal(modalPais);
        esperarCargaCompletaPagina();
        controlarMensajeToaster(mensajeToaster, cambioDireccion);
    }

    public String recuperarTexto() {
        obtenerTexto(textoResultados);
        return obtenerTexto(textoResultados);
    }

    public boolean buscadorDisponible() {
        return elementoVisible(buscador, 5);
    }

    public boolean seccionResultadosDisponible() {
        return elementoVisible(seccionResultados, 5);
    }

    public Boolean valorEsperado() {
        return obtenerValor(precio);
    }

    public Integer valorEsperadoCantidad() {
        return obtenerCantidad(precio);
    }
    
}
