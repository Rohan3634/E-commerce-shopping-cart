import java.util.*;

public class ShoppingCartSystem {

    // Product class
    static class Product {
        private int id;
        private String name;
        private double price;

        public Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public double getPrice() { return price; }

        @Override
        public String toString() {
            return id + ". " + name + " - $" + price;
        }
    }

    // CartItem class
    static class CartItem {
        private Product product;
        private int quantity;

        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public void increaseQuantity(int amount) {
            this.quantity += amount;
        }

        public double getTotalPrice() {
            return product.getPrice() * quantity;
        }

        @Override
        public String toString() {
            return product.getName() + " x " + quantity + " = $" + String.format("%.2f", getTotalPrice());
        }
    }

    // Main program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Product> products = new HashMap<>();
        Map<Integer, CartItem> cart = new HashMap<>();

        // Add some products
        products.put(1, new Product(1, "Laptop", 899.99));
        products.put(2, new Product(2, "Phone", 499.49));
        products.put(3, new Product(3, "Headphones", 59.99));
        products.put(4, new Product(4, "Keyboard", 29.99));
        products.put(5, new Product(5, "Mouse", 19.99));

        boolean running = true;
        while (running) {
            System.out.println("\n==== Welcome to the E-Commerce Store ====");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Remove Product from Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // View products
                    System.out.println("\nAvailable Products:");
                    for (Product product : products.values()) {
                        System.out.println(product);
                    }
                    break;

                case 2:
                    // Add to cart
                    System.out.println("\nEnter Product ID to add:");
                    int addId = scanner.nextInt();
                    if (products.containsKey(addId)) {
                        Product product = products.get(addId);
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        if (cart.containsKey(addId)) {
                            cart.get(addId).increaseQuantity(quantity);
                        } else {
                            cart.put(addId, new CartItem(product, quantity));
                        }
                        System.out.println("Added " + quantity + " x " + product.getName() + " to cart.");
                    } else {
                        System.out.println("Product not found!");
                    }
                    break;

                case 3:
                    // View cart
                    if (cart.isEmpty()) {
                        System.out.println("\nYour cart is empty.");
                    } else {
                        System.out.println("\nYour Cart:");
                        double total = 0;
                        for (CartItem item : cart.values()) {
                            System.out.println(item);
                            total += item.getTotalPrice();
                        }
                        System.out.println("Total: $" + String.format("%.2f", total));
                    }
                    break;

                case 4:
                    // Remove from cart
                    System.out.println("\nEnter Product ID to remove:");
                    int removeId = scanner.nextInt();
                    if (cart.containsKey(removeId)) {
                        cart.remove(removeId);
                        System.out.println("Product removed from cart.");
                    } else {
                        System.out.println("Product not found in cart!");
                    }
                    break;

                case 5:
                    // Checkout
                    if (cart.isEmpty()) {
                        System.out.println("\nYour cart is empty.");
                    } else {
                        System.out.println("\nChecking out. Final Cart:");
                        double total = 0;
                        for (CartItem item : cart.values()) {
                            System.out.println(item);
                            total += item.getTotalPrice();
                        }
                        System.out.println("Total to Pay: $" + String.format("%.2f", total));
                        System.out.println("Thank you for your purchase!");
                        cart.clear();
                    }
                    break;

                case 6:
                    running = false;
                    System.out.println("Exiting... Thank you for visiting!");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}

