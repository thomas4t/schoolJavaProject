package cz.uhk.pro1.cashdesk.model;

//import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Receipt {

    private List<ReceiptItem> items = new ArrayList<>();
    private Date date = new Date();

    public List<ReceiptItem> getItems() {
        return items;
    }

    public Date getDate() {
        return date;
    }

    public void addItem(Product product, double quantity) {
        items.add(new ReceiptItem(product, quantity));
    }

    public void addItem(ReceiptItem item) {
        items.add(item);
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (ReceiptItem item : items) {
            totalPrice += item.getItemPrice();
        }
        return totalPrice;
    }

    public void print() {
        System.out.println(new SimpleDateFormat("d.M.yyyy HH:mm:ss").format(date));
        System.out.println("---------------");
        for (ReceiptItem item : items) {
            System.out.println(item);
        }
        System.out.println("---------------");
        System.out.printf("CELKEM   %.2f Kč%n", getTotalPrice());
//        System.out.println("CELKEM   " + new DecimalFormat("0.00").format(getTotalPrice()) + " Kč");
    }

}
