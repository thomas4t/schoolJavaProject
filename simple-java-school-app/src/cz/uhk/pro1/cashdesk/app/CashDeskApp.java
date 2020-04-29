package cz.uhk.pro1.cashdesk.app;

import cz.uhk.pro1.cashdesk.model.Catalog;
import cz.uhk.pro1.cashdesk.model.Product;
import cz.uhk.pro1.cashdesk.model.Receipt;
//import cz.uhk.pro1.cashdesk.services.CatalogCsvStorage;
import cz.uhk.pro1.cashdesk.services.CatalogObjectStorage;
import cz.uhk.pro1.cashdesk.services.CatalogStorage;

public class CashDeskApp {

    public static void main(String[] args) {

        Catalog catalog = new Catalog();
        catalog.addProduct(new Product("rohlík", "123456X1", "ks", 1.90));
        catalog.addProduct(new Product("jablko", "123456X2", "kg", 30.00));
        catalog.addProduct(new Product("pivo", "123456X3", "l", 28.00));

//        CatalogStorage storage = new CatalogCsvStorage("catalog.csv");
        CatalogStorage storage = new CatalogObjectStorage("catalog.bin");
        storage.save(catalog);
        catalog = storage.load();

        Receipt receipt = new Receipt();
        receipt.addItem(catalog.getProducts().get(0), 10);
        receipt.addItem(catalog.getProducts().get(1), 1.5);
        Product product = catalog.getProductById("123456X3");
        if (product != null) {
            receipt.addItem(product, 0.5);
        }

        receipt.print();

    }

}
