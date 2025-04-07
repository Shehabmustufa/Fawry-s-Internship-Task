package Customer;

public class Customer {

    private String CustomerName;
    private double CustomerBalance;

    public Customer(String customerName, double customerBalance) {
        this.CustomerName = customerName;
        this.CustomerBalance = customerBalance;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public double getCustomerBalance() {
        return CustomerBalance;
    }

    public void deductBalance(double amount) {
        if (amount > 0 && this.CustomerBalance >= amount) {
            this.CustomerBalance -= amount;
        } else {

            System.err.println("Error: Attempted to deduct invalid amount or insufficient funds within deductBalance.");
        }
    }
}