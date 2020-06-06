package me.divisa.bankscraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

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
        ArrayList<Double> cotizaciones = new ArrayList<>();

        // Let's go
        NAVEGADOR.navigate().to(TARGET_URL);

        // Esperamos que renderice la tabla
        ESPERA.until(ExpectedConditions.visibilityOfElementLocated(By.className("herramientas_cotizaciones")));

        // El Ciudad tiene mucho nesting sin patrones específicos, vamos a buscar por ID
        String[] idsAEncontrar = {
                "cotizacion_dolar_compra",
                "cotizacion_dolar_venta",
                "cotizacion_euro_compra",
                "cotizacion_euro_venta",
                "cotizacion_real_compra",
                "cotizacion_real_venta"
        };

        // Formateamos los valores de cada ID, los parseamos y los agregamos al ArrayList de cotizaciones
        for (String id : idsAEncontrar)
            cotizaciones.add(
              Double.parseDouble(
                      NAVEGADOR.findElement(By.id(id)).getText().substring(2).replace(",", ".")
              ));

        // Devolvemos el ArrayList como double[]
        return cotizaciones.stream().mapToDouble(Double::doubleValue).toArray();
    }

}
