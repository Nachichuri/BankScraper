package me.divisa.bankscraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ScraperBbva {

    // Seteamos la página de destino y el elemento a scrapear, e inicializamos el array donde vamos a guardar las cotizaciones
    private final String TARGET_URL = "https://hb.bbv.com.ar/fnet/mod/inversiones/NL-dolareuro.jsp";
    private double[] cotizaciones = new double[8];
    private byte count = 0;

    // Instanciamos objetos para el webdriver y las opciones
    private final FirefoxOptions opcionesFirefox = new FirefoxOptions().setHeadless(true);
    private FirefoxDriver driver = new FirefoxDriver(opcionesFirefox);
    private WebDriverWait espera = new WebDriverWait(driver, 30);

    public void scrape() {
        // Let's go, es dificil armar un método para scrapear sin que quede tan procedural, pero bueno:
        driver.navigate().to(TARGET_URL);

        // Esperamos que renderice la tabla y la guardamos en una lista de objetos scrapeados
        espera.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
        List<WebElement> tabla = driver.findElements(By.className("td2"));

        // Agregamos los valores scrapeados en la lista
        for (WebElement item : tabla) {
            double cotizacion = Double.parseDouble(item.getText().replace(",", "."));
            cotizaciones[count] = cotizacion;
            count++;
        }
        // Cerramos el navegador
        driver.close();
    }

    public double[] getList() {
        scrape();
        return cotizaciones;
    }
}
