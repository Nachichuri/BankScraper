package me.divisa.bankscraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ScraperPatagonia implements Scraper {

    private final String TARGET_URL;
    private final WebDriver NAVEGADOR;
    private final WebDriverWait ESPERA;

    // Constructor
    public ScraperPatagonia(WebDriver driver, WebDriverWait wait) {
        this.TARGET_URL = "https://ebankpersonas.bancopatagonia.com.ar/eBanking/usuarios/cotizacionMonedaExtranjera.htm";
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
        ESPERA.until(ExpectedConditions.visibilityOfElementLocated(By.id("principal")));
        List<WebElement> tabla = NAVEGADOR.findElements(By.tagName("td"));

        // Descartamos los <td> que no nos interesan y parseamos los que nos sirven:
        tabla.forEach(d -> {
            try {
                cotizaciones.add(Double.parseDouble(d.getText().replace(",", ".").substring(2)));
            } catch (NumberFormatException | StringIndexOutOfBoundsException ignored) {}
        }); // Medio desprolijo pero por lo pronto cubre los TD vacíos y con Strings

        // Devolvemos el ArrayList como un double[]
        return cotizaciones.stream().mapToDouble(Double::doubleValue).toArray();
    }
}