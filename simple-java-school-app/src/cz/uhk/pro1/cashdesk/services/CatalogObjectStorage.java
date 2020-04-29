package cz.uhk.pro1.cashdesk.services;

import cz.uhk.pro1.cashdesk.model.Catalog;

import java.io.*;

public class CatalogObjectStorage implements CatalogStorage {

    private String filename;

    public CatalogObjectStorage(String filename) {
        this.filename = filename;
    }

    @Override
    public void save(Catalog catalog) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename))) {
            os.writeObject(catalog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Catalog load() {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename))) {
            return (Catalog) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Catalog();
    }

}
