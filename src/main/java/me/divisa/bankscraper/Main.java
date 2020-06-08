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

        Scraper bbva = new ScraperBbva(driver, espera);
        Scraper ciudad = new ScraperCiudad(driver, espera);
        Scraper galicia = new ScraperGalicia(driver, espera);
        Scraper icbc = new ScraperIcbc(driver, espera);
        Scraper nacion = new ScraperNacion(driver, espera);
        Scraper patagonia = new ScraperPatagonia(driver, espera);
        Scraper provincia = new ScraperProvincia(driver, espera);
        Scraper santander = new ScraperSantander(driver, espera);

        System.out.println("Cotizaciones BBVA:");
        System.out.println(Arrays.toString(bbva.getList()));

        System.out.println("Cotizaciones Ciudad:");
        System.out.println(Arrays.toString(ciudad.getList()));

        System.out.println("Cotizaciones Galicia:");
        System.out.println(Arrays.toString(galicia.getList()));

        System.out.println("Cotizaciones ICBC:");
        System.out.println(Arrays.toString(icbc.getList()));

        System.out.println("Cotizaciones Nación:");
        System.out.println(Arrays.toString(nacion.getList()));

        System.out.println("Cotizaciones Patagonia:");
        System.out.println(Arrays.toString(patagonia.getList()));

        System.out.println("Cotizaciones Provincia:");
        System.out.println(Arrays.toString(provincia.getList()));

        System.out.println("Cotizaciones Santander:");
        System.out.println(Arrays.toString(santander.getList()));

        //Cerramos el navegador
        driver.close();
    }

}