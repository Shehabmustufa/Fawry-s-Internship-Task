package Products;

import java.time.LocalDate;

public class Product implements Shippable{

    private String productName;
    private double productPrice;
    private int quantity;

    private boolean hasExpirationDate;
    private boolean isShippable ;

    private double weight;
    private LocalDate expirationDate;



    public Product(){

    }

    public Product(String productName, double productPrice, int quantity,
                   boolean hasExpirationDate, LocalDate expirationDate,
                   boolean isShippable, double weight) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.hasExpirationDate = hasExpirationDate;
        this.expirationDate = this.hasExpirationDate ? expirationDate : null;
        this.isShippable = isShippable;
        this.weight = this.isShippable ? weight : 0.0;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isHasExpirationDate() {
        return hasExpirationDate;
    }

    public boolean isShippable() {
        return isShippable;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String getName() {
        return productName;
    }

    @Override
    public double getWeight() {
        return weight;
    }
    public boolean isExpired() {
        if (!hasExpirationDate || expirationDate == null) {
            return false;
        }
        return LocalDate.now().isAfter(expirationDate);
    }

    public boolean decreaseQuantity(int amount) {
        if (amount > 0 && this.quantity >= amount) {
            this.quantity -= amount;
            return true;
        }
        return false;
    }
}
