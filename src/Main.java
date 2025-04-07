import Cart.Cart;
import Customer.Customer;
import Products.ProductRepository;
import Services.CheckoutService;
import Services.ShippingService;

public class Main {
    public static void main(String[] args) {

        Customer customer1 = new Customer("Shehab", 5000.0);
        Cart cart = new Cart();
        ShippingService shippingService = new ShippingService();
        CheckoutService checkoutService = new CheckoutService(shippingService);

        System.out.println("SCENARIO 1: Standard Checkout");
        cart.addProduct(ProductRepository.CHEESE, 2);
        cart.addProduct(ProductRepository.BISCUITS, 1);
        cart.addProduct(ProductRepository.TV, 1);
        cart.addProduct(ProductRepository.SCRATCH_CARD, 3);

        checkoutService.checkout(customer1, cart);

        System.out.println("\nSCENARIO 2: Insufficient Funds");
        Customer customer2 = new Customer("Seif", 100.0);
        Cart cart2 = new Cart();
        cart2.addProduct(ProductRepository.TV, 1);
        checkoutService.checkout(customer2, cart2);


        System.out.println("\nSCENARIO 3: Out of Stock");
        Cart cart3 = new Cart();

        cart3.addProduct(ProductRepository.TV, 1);
        cart3.addProduct(ProductRepository.TV, 1);
        boolean addedSuccessfully = cart3.addProduct(ProductRepository.TV, 1);
        if (addedSuccessfully) {
            checkoutService.checkout(customer1, cart3);
        } else {
            System.out.println("Could not add third TV due to insufficient stock during addProduct.");
        }


    }
}