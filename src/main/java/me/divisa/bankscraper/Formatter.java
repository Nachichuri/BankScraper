package me.divisa.bankscraper;

public class Formatter {
    private String resultado;

    public void format(double[] cotizaciones) {

        for (double item : cotizaciones) {
            System.out.println("$" + item);
        }
    }

}
