package vendingMachineDemo;

public class Main {
    public static void main(String[] args) {
        Product[] products = { new Product("Soda", 20), new Product("Chips", 15), new Product("Candy", 10) };
        int[] stock = { 10, 10, 10 };
        int initialCash = 1000;
        VendingMachine vendingMachine = new VendingMachine(products, stock, initialCash);
        VendingMachineService vendingMachineService = new VendingMachineService(vendingMachine);
        vendingMachineService.start();
    }
}
