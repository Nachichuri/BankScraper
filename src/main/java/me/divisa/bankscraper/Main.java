package me.divisa.bankscraper;

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

        System.out.println("Cotizaciones BBVA:");
        System.out.println(Arrays.toString(bbva.getList()));

        System.out.println("Cotizaciones Ciudad:");
        System.out.println(Arrays.toString(ciudad.getList()));

        System.out.println("Cotizaciones Galicia:");
        System.out.println("Falta hacer :(");

        System.out.println("Cotizaciones ICBC:");
        System.out.println(Arrays.toString(icbc.getList()));

        //Cerramos el navegador
        driver.close();
    }

}