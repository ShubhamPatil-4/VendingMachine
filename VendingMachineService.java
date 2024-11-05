package vendingMachineDemo;

import java.util.Scanner;

public class VendingMachineService {

    private VendingMachine vendingMachine;
    private Scanner scanner;

    public VendingMachineService(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            displayProducts();
            System.out.print("Select product number to buy (or type -1 to exit): ");
            int productIndex = scanner.nextInt();

            if (productIndex == -1) {
                System.out.println("Exiting...");
                break;
            }

            if (productIndex < 0 || productIndex >= vendingMachine.getProducts().length) {
                System.out.println("Invalid product number.");
                continue;
            }

            if (vendingMachine.getStock(productIndex) <= 0) {
                System.out.println("Sorry, this product is out of stock.");
                continue;
            }

            Product product = vendingMachine.getProducts()[productIndex];
            System.out.println("Price of " + product.getName() + " is Rs." + product.getPrice());
            int totalInserted = getPayment();

            if (totalInserted < product.getPrice()) {
                System.out.println("Insufficient amount. Please insert more money.");
                // Optionally, you could refund the inserted money here
                continue;
            }

            int change = totalInserted - product.getPrice();
            vendingMachine.subtractCash(product.getPrice());
            vendingMachine.reduceStock(productIndex);
            System.out.println("Purchase successful!");
            if (change > 0) {
                vendingMachine.giveChange(change);
            }
            System.out.println("Remaining cash in the machine: Rs." + vendingMachine.getCash());
        }
    }

    private int getPayment() {
        int totalAmount = 0;
        System.out.println("Insert coins (Accepted: Rs. 5, 10, 15, 20). Type 0 to finish inserting coins.");

        while (true) {
            int coin = scanner.nextInt();
            if (coin == 0) {
                break;
            } else if (coin == 5 || coin == 10 || coin == 15 || coin == 20) {
                totalAmount += coin;
                vendingMachine.addCash(coin);
                System.out.println("Inserted Rs." + coin);
            } else {
                System.out.println("Invalid coin. Please insert Rs. 5, 10, 15, or 20.");
            }
        }
        return totalAmount;
    }

    private void displayProducts() {
        Product[] products = vendingMachine.getProducts();
        System.out.println("Available products:");
        for (int i = 0; i < products.length; i++) {
            if (vendingMachine.getStock(i) > 0) {
                System.out.println(i + ". " + products[i] + " (Stock: " + vendingMachine.getStock(i) + ")");
            }
        }
    }
}
