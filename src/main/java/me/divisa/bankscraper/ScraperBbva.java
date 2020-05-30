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
    private byte count = 0;

    // Instanciamos objetos para el webdriver y las opciones
    private final FirefoxOptions opcionesFirefox = new FirefoxOptions().setHeadless(true);
    private FirefoxDriver driver = new FirefoxDriver(opcionesFirefox);
    private WebDriverWait espera = new WebDriverWait(driver, 30);

    public double[] scrape() {
        double[] cotizaciones = new double[8];
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
        return cotizaciones;
    }

    public double[] getList() {
        double[] lista = scrape();
        return ordenarLista(lista);
    }

    public double[] ordenarLista(double[] listaDesordenada) {
        // Esto es un horror, revisar mañana
        double[] listaOrdenada = new double[8];

        for (byte i = 0; i < 4; i++) {
            listaOrdenada[i] = listaDesordenada[i];
        }
        listaOrdenada[4] = listaDesordenada[6];
        listaOrdenada[5] = listaDesordenada[7];
        listaOrdenada[6] = listaDesordenada[4];
        listaOrdenada[7] = listaDesordenada[5];

        return listaOrdenada;
    }
}
