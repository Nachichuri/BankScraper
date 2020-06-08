package me.divisa.bankscraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ScraperGalicia implements Scraper {

    private final String TARGET_URL;
    private final WebDriver NAVEGADOR;
    private final WebDriverWait ESPERA;

    // Constructor
    public ScraperGalicia(WebDriver driver, WebDriverWait wait) {
        this.TARGET_URL = "https://www.bancogalicia.com/banca/online/web/Personas/ProductosyServicios/Cotizador";
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
        ESPERA.until(ExpectedConditions.visibilityOfElementLocated(By.className("tabla")));
        List<WebElement> tabla = NAVEGADOR.findElements(By.tagName("li"));

        // Hay que dar bastantes vueltas para sacar los valores, pero lo hacemos asi:
        for (WebElement item : tabla)
            item.findElements(By.tagName("div")).stream()
                    .forEach(cotizacion -> {
                        try {
                            cotizaciones.add(Double.parseDouble(cotizacion.getText().replace(",", ".")));
                        } catch (NumberFormatException ignored) {}
                    });

        // Devolvemos el ArrayList como un double[]
        return cotizaciones.stream().mapToDouble(Double::doubleValue).toArray();
    }
}