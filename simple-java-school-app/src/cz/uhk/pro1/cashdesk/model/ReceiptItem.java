package cz.uhk.pro1.cashdesk.model;

public class ReceiptItem {

    private Product product;
    private double quantity;

    public ReceiptItem(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getItemPrice() {
        return product.getUnitPrice() * quantity;
    }

    @Override
    public String toString() {
        return product.getName() + "   " + quantity + " * " + product.getUnitPrice()
                + " Kč/" + product.getUnit() + "   " + getItemPrice() + " Kč";
    }

}
