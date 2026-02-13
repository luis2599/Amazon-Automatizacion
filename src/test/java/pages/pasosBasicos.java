package pages;
// Importación de las librerias necesarias para el funcionamiento de: WebDriver, manejo de elementos web, y gestión de tiempos de espera.
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//importación de las clases necesarias para el manejo de Selenium WebDriver y WebDriverManager.
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//importación de la clase WebDriverManager para gestionar las dependencias del controlador del navegador.
import io.github.bonigarcia.wdm.WebDriverManager;


public class pasosBasicos{

    //Declaración de la variable estática 'driver' para el WebDriver y una instancia de WebDriverWait con un tiempo de espera de 5 segundos.
    protected static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    //Configuración del WebDriver para Chrome usando WebDriverManager.
    static{
        //estas lineas son para ingreso en modo incognito y asi no genere problema con el login
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>(); 
        prefs.put("credentials_enable_service", false); 
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs); // opcionales para reducir detección de automatización options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation")); options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--incognito");
        options.addArguments("--window-size=1920,1080");
        WebDriverManager.chromedriver().setup();
        //inicializa el driver de Chrome con las opciones configuradas
        driver = new ChromeDriver(options);
        //elimina la propiedad navigator.webdriver para evitar la detección de automatización
        ((JavascriptExecutor) driver).executeScript(
        "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
        );
        //maximiza la ventana del navegador
        driver.manage().window().maximize();
        driver.manage().getCookies();
    }

    //Inicializa la variable estática 'driver' con una instancia de ChromeDriver
    public pasosBasicos(WebDriver driver){
        pasosBasicos.driver = driver;
    }

    //metodo para dar navegar a un sitio web
    public static void navigateTo(String url) {
        driver.get(url);
    }

    //metodo para esperar un tiempo determinado (en segundos) utilizando Thread.sleep, con manejo de excepciones.
    public void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //metodo para encontrar un elemento web utilizando un localizador
    private WebElement encontrar(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        if (driver.findElements(locator).size() > 0) {
            System.out.println("Elemento encontrado: " + locator.toString());
            return driver.findElement(locator);
        } else {
            throw new RuntimeException("Elemento no encontrado: " + locator.toString());
        }
    }

    //metodo para obtener la URL actual del navegador
    public void obtenerURL(){
        String urlActual = driver.getCurrentUrl();
        System.out.println("URL actual: " + urlActual);
    }

    //metodo esperar ventana emergente y cambiar a ella
    public void cambiarAVentanaEmergente(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //metodo para cambiar a la ventana principal
    public void cambiarAVentanaPrincipal(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    //metodo para hacer clic en un elemento web utilizando un localizador
    public void click(By locator) {
        encontrar(locator).click();
    }

    //metodo para escribir texto en un elemento web utilizando un localizador
    public void escribirTexto(By locator, String texto) {
        encontrar(locator).clear();
        encontrar(locator).sendKeys(texto);
    }

    //metodo para dar clic con la tecla enter
    public void enter(By locator) {
        encontrar(locator).sendKeys(Keys.ENTER);
    }

    //metodo para esperar a que un elemento web esté presente en la página utilizando un localizador
    public void esperarElemento(By locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        // Usar JavaScript para interactuar con el select oculto
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Hacer visible temporalmente el select
        js.executeScript(
            "var select = document.getElementById('locator');" +
            "select.style.display = 'block';" +
            "select.style.visibility = 'visible';" +
            "select.style.opacity = '1';" +
            "select.style.height = 'auto';" +
            "select.style.width = 'auto';"
        );
    }

    //metodo para seleccionar un valor de lista despegable por valor de la lista
    public void seleccionarXValor(By locator, String valor){
        esperarElemento(locator);
        System.out.println("Esta llegando aqui");
        Select dropdown = new Select(encontrar(locator));
        dropdown.selectByValue(valor);
    }

    

    //metodo para seleccionar un valor de lista despegable por numero de orden
    public void seleccionarXOrden(By locator, int valor){
        Select dropdown = new Select(encontrar(locator));
        dropdown.selectByIndex(valor);
    }

    //Metodo para tomar un objeto de la pagina y transformala en texto
    public String obtenerTexto(By locator){ 
        String texto = driver.findElement(locator).getText(); 
        return texto;
    }

    public String obtenerTextoWebElement(WebElement locator){ 
        String texto = locator.getText();
        System.out.println("Texto obtenido: " + texto);
        return texto;
    }

    //metodo para verificar si un elemento es visible en la pagina
    public boolean elementoVisible(By locator) {
        try {
            return encontrar(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //metodo para tomar el valor de una tabla
    public Boolean obtenerValor(By locator) {
        Boolean elemento = driver.findElements(locator).size() > 0;
        System.out.println("Valor obtenido: " + elemento);
        return elemento;
    }

    //metodo para tomar la cantidad de elementos encontrados en una tabla
    public Integer obtenerCantidad(By locator) {
        Integer elemento = driver.findElements(locator).size();
        System.out.println("Valor obtenido: " + elemento);
        return elemento;
    }

    //metodo para cerrar el navegador
    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void refrescarPagina() {
        driver.navigate().refresh();
        System.out.println("Página refrescada");
    }
    
}
