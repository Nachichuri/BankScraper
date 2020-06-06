package me.divisa.bankscraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ScraperIcbc implements Scraper {

    private final String TARGET_URL;
    private final WebDriver NAVEGADOR;
    private final WebDriverWait ESPERA;

    // Constructor
    public ScraperIcbc(WebDriver driver, WebDriverWait wait) {
        this.TARGET_URL = "https://www.icbc.com.ar/personas";
        this.NAVEGADOR = driver;
        this.ESPERA = wait;
    }

    // Interface overriding
    @Override
    public double[] getList() {
        return scrape();
    }

    public double[] scrape() {
        ArrayList<Double> cotizaciones = new ArrayList<Double>();

        // Let's go
        NAVEGADOR.navigate().to(TARGET_URL);

        // Esperamos que renderice la tabla y la guardamos en una lista de objetos scrapeados
        ESPERA.until(ExpectedConditions.visibilityOfElementLocated(By.className("moneda-datos")));
        List<WebElement> tabla = NAVEGADOR.findElements(By.className("moneda--valor"));

        // Como devuelven strings con mas caracteres, lo que hacemos es iterar sobre cada uno, crear substrings
        // y después parsearlos y agregarlos al ArrayList
        for (WebElement item : tabla) {
            cotizaciones.add(0,
                    Double.parseDouble(
                    item.getText()
                        .substring(item.getText().indexOf("$")+1, item.getText().indexOf("$")+6)
                        .replace(",", ".")
                    ));
        }

        // Creamos un stream con el ArrayList y devolvemos los valores en un double[]
        return cotizaciones.stream().mapToDouble(Double::doubleValue).toArray();
    }
}
