package pages;
// Importación de las librerias necesarias para el funcionamiento de: WebDriver, manejo de elementos web, y gestión de tiempos de espera.
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//importación de las clases necesarias para el manejo de Selenium WebDriver y WebDriverManager.
import org.openqa.selenium.By;
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
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    //Configuración del WebDriver para Chrome usando WebDriverManager.
    static{
        //estas lineas son para ingreso en modo incognito y asi no genere problema con el login
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>(); 
        prefs.put("credentials_enable_service", false); 
        prefs.put("profile.password_manager_enabled", false); 
        options.setExperimentalOption("prefs", prefs); // opcionales para reducir detección de automatización options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation")); options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--incognito");
        WebDriverManager.chromedriver().setup();
        //inicializa el driver de Chrome con las opciones configuradas
        driver = new ChromeDriver(options);
        //maximiza la ventana del navegador
        driver.manage().window().maximize();
    }

    //Inicializa la variable estática 'driver' con una instancia de ChromeDriver
    public pasosBasicos(WebDriver driver){
        pasosBasicos.driver = driver;
    }

    //metodo para dar navegar a un sitio web
    public static void navigateTo(String url) {
        driver.get(url);
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

    //metodo para cerrar el navegador
    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
    
}
