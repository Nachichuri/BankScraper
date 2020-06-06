package me.divisa.bankscraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ScraperNacion implements Scraper {

    private final String TARGET_URL;
    private final WebDriver NAVEGADOR;
    private final WebDriverWait ESPERA;

    // Constructor
    public ScraperNacion(WebDriver driver, WebDriverWait wait) {
        this.TARGET_URL = "https://www.bna.com.ar/Personas";
        this.NAVEGADOR = driver;
        this.ESPERA = wait;
    }

    // Interface overriding
    @Override
    public double[] getList() {
        return scrape();
    }

    private double[] scrape() {
        ArrayList<Double> cotizaciones = new ArrayList<>();

        // Let's go
        NAVEGADOR.navigate().to(TARGET_URL);

        // Esperamos que renderice la tabla y la guardamos en una lista de objetos scrapeados
        ESPERA.until(ExpectedConditions.visibilityOfElementLocated(By.className("cotizacion")));
        List<WebElement> tabla = NAVEGADOR.findElements(By.tagName("td"));

        // Descartamos los <td> que no nos interesan y parseamos los que nos sirven:
        tabla.forEach(d -> {
            try {
                cotizaciones.add(Double.parseDouble(d.getText().replace(",", ".")));
            } catch (NumberFormatException ignored) {}
        }); // Medio desprolijo pero por lo pronto cumple la funcion
        // El Nacion por algun motivo muestra los reales cada 100 unidades, asi que formateamos
        cotizaciones.set(4, cotizaciones.get(4)/100);
        cotizaciones.set(5, cotizaciones.get(5)/100);

        // Devolvemos el ArrayList como un double[]
        return cotizaciones.stream().mapToDouble(Double::doubleValue).toArray();
    }
}
