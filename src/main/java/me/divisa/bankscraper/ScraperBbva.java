package me.divisa.bankscraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScraperBbva implements Scraper {

    private final String TARGET_URL;
    private final WebDriver NAVEGADOR;
    private final WebDriverWait ESPERA;

    // Constructor
    public ScraperBbva(WebDriver driver, WebDriverWait wait) {
        this.TARGET_URL = "https://hb.bbv.com.ar/fnet/mod/inversiones/NL-dolareuro.jsp";
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
        ESPERA.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
        List<WebElement> tabla = NAVEGADOR.findElements(By.className("detalles"));

        // Streameamos los valores y los agregamos al ArrayList formateados como Double
        tabla.forEach(d -> cotizaciones.add(Double.parseDouble(d.getText().replace(",", "."))));
        // Pasamos los Pesos Uruguayos al final para que quede igual que el resto de los bancos
        Collections.swap(cotizaciones, 4, 6);
        Collections.swap(cotizaciones, 5, 7);

        // Devolvemos el ArrayList como un double[]
        return cotizaciones.stream().mapToDouble(Double::doubleValue).toArray();
    }
}
