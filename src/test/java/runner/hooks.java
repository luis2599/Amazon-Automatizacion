package runner;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import pages.pasosBasicos;

public class hooks extends pasosBasicos {

    public hooks() {
        super(driver);
    }

    @After
    public void tearDown(Scenario scenario){
        // Capturar evidencia siempre
        final byte[] evidencia = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
        scenario.attach(evidencia, "image/png", "Evidencia de prueba");
        if(scenario.isFailed()){
        System.out.println("Escenario fallo: se tomó captura de la pantalla de error. " + scenario.getName());
        } else {
        System.out.println("Escenario exitoso: se tomó captura de la pantalla de error. " + scenario.getName());
        }    
    }

}
