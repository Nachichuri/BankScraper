package me.divisa.bankscraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ScraperBbva implements Scraper {

    private final String TARGET_URL;
    private final WebDriver NAVEGADOR;
    private final WebDriverWait ESPERA;

    // Constructors
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

    public double[] scrape() {
        byte count = 0;
        double[] cotizaciones = new double[8];
        // Let's go, es dificil armar un método para scrapear sin que quede tan procedural, pero bueno:
        NAVEGADOR.navigate().to(TARGET_URL);

        // Esperamos que renderice la tabla y la guardamos en una lista de objetos scrapeados
        ESPERA.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
        List<WebElement> tabla = NAVEGADOR.findElements(By.className("td2"));

        // Agregamos los valores scrapeados en la lista
        for (WebElement item : tabla) {
            double cotizacion = Double.parseDouble(item.getText().replace(",", "."));
            cotizaciones[count] = cotizacion;
            count++;
        }
        // Cerramos el navegador


        return ordenarLista(cotizaciones);
    }

    public double[] ordenarLista(double[] listaDesordenada) {
        // Esto es un horror, revisar
        double[] listaOrdenada = new double[8];

        System.arraycopy(listaDesordenada, 0, listaOrdenada, 0, 4);
        listaOrdenada[4] = listaDesordenada[6];
        listaOrdenada[5] = listaDesordenada[7];
        listaOrdenada[6] = listaDesordenada[4];
        listaOrdenada[7] = listaDesordenada[5];

        return listaOrdenada;
    }

}
