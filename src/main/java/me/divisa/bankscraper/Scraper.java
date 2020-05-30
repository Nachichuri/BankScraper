package me.divisa.bankscraper;

public interface Scraper {

    // Getter para las cotizaciones scrapeadas
    // Siempre debería ser la lista de doubles en el siguiente orden:
    // [USD_COMPRA, USD_VENTA, EURO_COMPRA, EURO_VENTA, REAL_COMPRA, REAL_VENTA, PESOURU_COMPRA, PESOURU_VENTA]
    double[] getList();

}
