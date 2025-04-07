package Services;

import Products.Product;
import java.util.Map;

public class ShippingService {

    public void send(Map<Product, Integer> shippableItemsWithQuantity) {
        if (shippableItemsWithQuantity == null || shippableItemsWithQuantity.isEmpty()) {
            return;
        }

        System.out.println("** Shipment notice **");
        double totalWeightKg = 0;
        boolean shippedItemsFound = false;

        for (Map.Entry<Product, Integer> entry : shippableItemsWithQuantity.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product.isShippable() && product.getWeight() > 0) {
                double itemTotalWeight = product.getWeight() * quantity;
                System.out.printf("%dx %s %.0fg%n", quantity, product.getName(), itemTotalWeight * 1000);
                totalWeightKg += itemTotalWeight;
                shippedItemsFound = true;
            }
        }

        if (shippedItemsFound) {
            System.out.printf("Total package weight %.1fkg%n", totalWeightKg);
        } else {
            System.out.println("No shippable items in this order.");
        }
    }
}