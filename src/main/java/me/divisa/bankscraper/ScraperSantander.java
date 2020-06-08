package me.divisa.bankscraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ScraperSantander implements Scraper {

    private final String TARGET_URL;
    private final WebDriver NAVEGADOR;
    private final WebDriverWait ESPERA;

    // Constructor
    public ScraperSantander(WebDriver driver, WebDriverWait wait) {
        this.TARGET_URL = "https://banco.santanderrio.com.ar/exec/cotizacion/index.jsp";
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
        ESPERA.until(ExpectedConditions.visibilityOfElementLocated(By.className("fortable")));
        List<WebElement> tabla = NAVEGADOR.findElements(By.tagName("td"));

        // Descartamos los <td> que no nos interesan y parseamos los que nos sirven:
        for (WebElement item : tabla)
            try {
                cotizaciones.add(Double.parseDouble(item.getText().substring(2).replace(",", ".")));
            } catch (NumberFormatException ignored) {}

        // Devolvemos el ArrayList como un double[]
        return cotizaciones.stream().mapToDouble(Double::doubleValue).toArray();
    }
}