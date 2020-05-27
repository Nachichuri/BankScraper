package me.divisa.bankscraper;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ScraperBbva bbva = new ScraperBbva();
        Formatter asd = new Formatter();
        double[] lista = bbva.getList();

        System.out.println("Imprimiendo BBVA:");
        System.out.println(Arrays.toString(lista));
        asd.format(lista);
    }

}