package cz.uhk.pro1.cashdesk.services;

import cz.uhk.pro1.cashdesk.model.Catalog;
import cz.uhk.pro1.cashdesk.model.Product;

import java.io.*;

public class CatalogCsvStorage implements CatalogStorage {

    private String filename;

    public CatalogCsvStorage(String filename) {
        this.filename = filename;
    }

    @Override
    public void save(Catalog catalog) {
//        BufferedWriter bw = null;
//        try { // try-catch-finally
//            bw = new BufferedWriter(new FileWriter(filename));
//            for (Product product : catalog.getProducts()){
//                bw.write(product.getName() + ";" + product.getId() + ";" + product.getUnit() + ";" + product.getUnitPrice());
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (bw != null) {
//                try {
//                    bw.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) { // try-with-resources
            for (Product product : catalog.getProducts()){
                bw.write(product.getName() + ";" + product.getId() + ";" + product.getUnit() + ";" + product.getUnitPrice());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Catalog load() {
        // TODO
        // make new catalog, load each line of .csv as .addProduct
        // eg.: catalog.addProduct(new Product("rohlík", "123456X1", "ks", 1.90));
        // return whole catalog

        String line = "";

        Catalog loadedCatalog = new Catalog();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] product = line.split(";");
                loadedCatalog.addProduct(new Product(product[0], product[1], product[2], Double.parseDouble(product[3])));
            }
            return loadedCatalog;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
