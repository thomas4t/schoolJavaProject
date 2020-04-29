package cz.uhk.pro1.cashdesk.app;

import cz.uhk.pro1.cashdesk.model.Catalog;
import cz.uhk.pro1.cashdesk.model.Product;
import cz.uhk.pro1.cashdesk.model.Receipt;
import cz.uhk.pro1.cashdesk.model.ReceiptItem;
import cz.uhk.pro1.cashdesk.services.CatalogCsvStorage;
import cz.uhk.pro1.cashdesk.services.CatalogObjectStorage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class CashDeskFrame extends JFrame {

    private Catalog catalog = new Catalog();
    private Receipt receipt = new Receipt();
    private JTextArea taCatalog = new JTextArea(10, 30);
    private JTextArea taReceipt = new JTextArea(10, 30);
    private JTextField tfProductId = new JTextField();
    private JSpinner spQuantity = new JSpinner(new SpinnerNumberModel(1.0,0.001,100.0,0.001));
    private JLabel lblTotalPrice = new JLabel();

    public CashDeskFrame() {
        populateCatalog();
        initializeGui();
    }

    private void populateCatalog() {
        catalog.addProduct(new Product("rohlík", "123456X1", "ks", 1.90));
        catalog.addProduct(new Product("jablko", "123456X2", "kg", 30.00));
        catalog.addProduct(new Product("pivo", "123456X3", "l", 28.00));

    }

    private void initializeGui() {
        setTitle("Pokladna");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        add(new JScrollPane(taCatalog));
        taCatalog.setEditable(false);
        refreshCatalog();

        JPanel pnlNewItem = new JPanel();
        pnlNewItem.setLayout(new GridLayout(2, 3));
        pnlNewItem.add(new JLabel("Kód produktu"));
        pnlNewItem.add(new JLabel("Množství"));

        JButton btnLoadCatalog = new JButton("Load csv");
        //Created a file chooser
        final JFileChooser fc = new JFileChooser();
        //Setting allowed .formats (csv)
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        fc.setFileFilter(filter);
        fc.setAcceptAllFileFilterUsed(false);

        btnLoadCatalog.addActionListener(new ActionListener() { //basic event listener
            @Override
            public void actionPerformed(ActionEvent e) {
                fc.setCurrentDirectory(new File(System.getProperty("user.dir"))); //setting file chooser's base directory

                int returnVal = fc.showOpenDialog(btnLoadCatalog);

                if (returnVal == JFileChooser.APPROVE_OPTION) { //double checking
                    File file = fc.getSelectedFile(); //selected .csv file
                    CatalogCsvStorage csvFile = new CatalogCsvStorage(file.getName()); //creating instance of CatalogCsvStorage class
                    catalog = csvFile.load(); //calling loading method to change current catalog
                    refreshCatalog(); //refreshing changed catalog
                } else {
                    JOptionPane.showMessageDialog(null, "Failed =C");
                }
            }
        });

        pnlNewItem.add(btnLoadCatalog);
        pnlNewItem.add(tfProductId);
        spQuantity.setEditor(new JSpinner.NumberEditor(spQuantity, "0.000"));
        pnlNewItem.add(spQuantity);
        JButton btnAddItem = new JButton("Přidat na účtenku");
        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });
//        btnAddItem.addActionListener(e -> addItem());
        pnlNewItem.add(btnAddItem);
        pnlNewItem.setMaximumSize(pnlNewItem.getMinimumSize());
        add(pnlNewItem);

        add(new JScrollPane(taReceipt));
        taReceipt.setEditable(false);
        taReceipt.setText(new SimpleDateFormat("d.M.yyyy HH:mm:ss").format(receipt.getDate()) + "\n\n");
        lblTotalPrice.setText("CELKEM   0 Kč");
        lblTotalPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblTotalPrice);

        pack();
    }

    private void refreshCatalog() {
        taCatalog.setText("");
        for (Product product : catalog.getProducts()) {
            taCatalog.append(product.toString() + "\n");
        }
    }

    private void addItem() {
        Product product = catalog.getProductById(tfProductId.getText());
        if (product != null) {
            ReceiptItem item = new ReceiptItem(product, (double) spQuantity.getValue());
            receipt.addItem(item);
            taReceipt.append(item.toString() + "\n");
            lblTotalPrice.setText("CELKEM   " + new DecimalFormat("0.00").format(receipt.getTotalPrice()) + " Kč");
            tfProductId.setText("");
            spQuantity.setValue(1.0);
        } else {
            JOptionPane.showMessageDialog(this, "Neznámý kód produktu");
        }
    }

}
