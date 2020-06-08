package me.divisa.bankscraper;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Inicializamos las dependencias para inyectar
        FirefoxOptions opcionesFirefox = new FirefoxOptions().setHeadless(true);
        FirefoxDriver driver = new FirefoxDriver(opcionesFirefox);
        WebDriverWait espera = new WebDriverWait(driver, 30);

        ScraperBbva bbva = new ScraperBbva(driver, espera);
        ScraperCiudad ciudad = new ScraperCiudad(driver, espera);
        ScraperIcbc icbc = new ScraperIcbc(driver, espera);
        Scraper nacion = new ScraperNacion(driver, espera);
        Scraper patagonia = new ScraperPatagonia(driver, espera);

        System.out.println("Cotizaciones BBVA:");
        System.out.println(Arrays.toString(bbva.getList()));

        System.out.println("Cotizaciones Ciudad:");
        System.out.println(Arrays.toString(ciudad.getList()));

        System.out.println("Cotizaciones Galicia:");
        System.out.println("Falta hacer :(");

        System.out.println("Cotizaciones ICBC:");
        System.out.println(Arrays.toString(icbc.getList()));

        System.out.println("Cotizaciones Nación:");
        System.out.println(Arrays.toString(nacion.getList()));

        System.out.println("Cotizaciones Patagonia:");
        System.out.println(Arrays.toString(patagonia.getList()));

        //Cerramos el navegador
        driver.close();
    }

}