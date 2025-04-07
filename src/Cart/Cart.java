package Cart;

import Products.Product;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> items = new HashMap<>();

    public boolean addProduct(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            System.err.println("Error: Invalid product or quantity.");
            return false;
        }
        if (product.getQuantity() < quantity) {
            System.err.println("Error: Not enough stock for " + product.getName() +
                    ". Available: " + product.getQuantity() + ", Requested: " + quantity);
            return false;
        }
        if (product.isExpired()) {
            System.err.println("Error: Product " + product.getName() + " is expired.");
            return false;
        }

        items.put(product, items.getOrDefault(product, 0) + quantity);
        System.out.println("Added " + quantity + "x " + product.getName() + " to cart.");
        return true;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double calculateSubtotal() {
        double subtotal = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            subtotal += entry.getKey().getProductPrice() * entry.getValue();
        }
        return subtotal;
    }

    public void clearCart() {
        items.clear();
    }
}