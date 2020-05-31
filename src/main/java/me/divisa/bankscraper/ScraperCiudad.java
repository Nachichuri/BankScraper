package me.divisa.bankscraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class ScraperCiudad implements Scraper {

    private final String TARGET_URL;
    private final WebDriver NAVEGADOR;
    private final WebDriverWait ESPERA;

    // Constructor
    public ScraperCiudad(WebDriver driver, WebDriverWait wait) {
        this.TARGET_URL = "https://www.bancociudad.com.ar/institucional/?herramienta=cotizaciones#";
        this.NAVEGADOR = driver;
        this.ESPERA = wait;
    }

    // Interface overriding
    @Override
    public double[] getList() {
        return scrape();
    }

    public double[] scrape() {
        byte count = 0;
        double[] cotizaciones = new double[6];

        // Let's go
        NAVEGADOR.navigate().to(TARGET_URL);

        // Esperamos que renderice la tabla y la guardamos en una lista de objetos scrapeados
        ESPERA.until(ExpectedConditions.visibilityOfElementLocated(By.className("herramientas_cotizaciones")));
        List<WebElement> tabla = NAVEGADOR.findElements(By.className("cotizacion__valor"));
        // Sacamos los primeros 3 WebElements que nos interesan y appendeamos compra y venta al array de cotizaciones
        for (byte i = 0; i < 3; i++) {
            String compra = tabla.get(i).findElement(By.className("cotizacion__valor--compra")).getText();
            cotizaciones[count] = Double.parseDouble(compra.substring(9).replace(",", "."));
            String venta = tabla.get(i).findElement(By.className("cotizacion__valor--venta")).getText();
            cotizaciones[count+1] = Double.parseDouble(venta.substring(8).replace(",", "."));
            count+=2;
        }

        return cotizaciones;
    }

}
