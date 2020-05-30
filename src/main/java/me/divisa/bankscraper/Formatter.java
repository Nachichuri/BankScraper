package me.divisa.bankscraper;

public class Formatter {
    // Hacer que esta clase escriba un archivo en el directorio del .jar con las cotizaciones scrapeadas

    private String resultado;

    public void format(double[] cotizaciones) {

        for (double item : cotizaciones) {
            System.out.println("$" + item);
        }
    }

}
