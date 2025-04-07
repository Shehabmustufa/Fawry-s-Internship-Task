package Services;

import Customer.Customer;
import Cart.Cart;
import Products.Product;
import java.util.HashMap;
import java.util.Map;

public class CheckoutService {

    private ShippingService shippingService;
    private static final double SHIPPING_FEE_FLAT = 30.0;

    public CheckoutService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public boolean checkout(Customer customer, Cart cart) {
        System.out.println("\n--- Starting Checkout for " + customer.getCustomerName() + " ---");

        if (cart.isEmpty()) {
            System.err.println("Checkout Error: Cart is empty.");
            return false;
        }

        Map<Product, Integer> items = cart.getItems();
        double subtotal = cart.calculateSubtotal();
        boolean hasShippableItems = false;
        Map<Product, Integer> shippableItemsForNotice = new HashMap<>();


        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantityNeeded = entry.getValue();

            if (product.getQuantity() < quantityNeeded) {
                System.err.println("Checkout Error: Product " + product.getName() + " is out of stock. Available: " + product.getQuantity());
                return false;
            }
            if (product.isExpired()) {
                System.err.println("Checkout Error: Product " + product.getName() + " is expired (" + product.getExpirationDate() + ").");
                return false;
            }
            if (product.isShippable()) {
                hasShippableItems = true;
                shippableItemsForNotice.put(product, quantityNeeded); // Add shippable item for notice
            }
        }


        double shippingFee = hasShippableItems ? SHIPPING_FEE_FLAT : 0.0;
        double totalAmount = subtotal + shippingFee;

        if (customer.getCustomerBalance() < totalAmount) {
            System.err.println("Checkout Error: Insufficient balance. Required: " + totalAmount + ", Available: " + customer.getCustomerBalance());
            return false;
        }


        customer.deductBalance(totalAmount);
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            entry.getKey().decreaseQuantity(entry.getValue());
        }

        if (hasShippableItems) {
            shippingService.send(shippableItemsForNotice);
        }

        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(quantity + "x " + product.getName() + " " + (product.getProductPrice() * quantity));
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + subtotal);
        if (hasShippableItems) {
            System.out.println("Shipping " + shippingFee);
        }
        System.out.println("Amount " + totalAmount);
        System.out.println("----------------------");
        System.out.println(customer.getCustomerName() + " new balance: " + customer.getCustomerBalance());


        cart.clearCart();
        System.out.println("--- Checkout Successful ---");
        return true;
    }
}