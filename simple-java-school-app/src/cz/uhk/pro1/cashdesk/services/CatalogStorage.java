package cz.uhk.pro1.cashdesk.services;

import cz.uhk.pro1.cashdesk.model.Catalog;

public interface CatalogStorage {

    public void save(Catalog catalog); //uložení katalogu do souboru

    public Catalog load(); //načtení katalogu ze souboru

}
