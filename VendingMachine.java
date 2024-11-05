package vendingMachineDemo;

public class VendingMachine {
    private Product[] products;
    private int[] stock;
    private int cash;

    public VendingMachine(Product[] products, int[] stock, int initialCash) {
        this.products = products;
        this.stock = stock;
        this.cash = initialCash;
    }

    public Product[] getProducts() {
        return products;
    }

    public int getStock(int index) {
        return stock[index];
    }

    public void reduceStock(int index) {
        if (stock[index] > 0) {
            stock[index]--;
        }
    }

    public int getCash() {
        return cash;
    }

    public void addCash(int amount) {
        cash += amount;
    }

    public void subtractCash(int amount) {
        cash -= amount;
    }

    public void giveChange(int amount) {
        System.out.println("Returning change: Rs." + amount);
    }
}
