import java.util.ArrayList;
import java.util.Scanner;

public class EcommerceApp {
    static ArrayList<Product> products = new ArrayList<>();
    static Cart cart = new Cart();

    public static void main(String[] args) {
        initializeProducts();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nWelcome to the E-commerce Store");
                System.out.println("1. View Products");
                System.out.println("2. Add to Cart");
                System.out.println("3. View Cart");
                System.out.println("4. Remove from Cart");
                System.out.println("5. Checkout");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> viewProducts();
                    case 2 -> addToCart(scanner);
                    case 3 -> cart.viewCart();
                    case 4 -> removeFromCart(scanner);
                    case 5 -> cart.checkout();
                    case 6 -> {
                        System.out.println("Thank you for visiting!");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            }
        }
    }

    private static void initializeProducts() {
        products.add(new Product(1, "Laptop", 800.0, 10));
        products.add(new Product(2, "Smartphone", 600.0, 20));
        products.add(new Product(3, "Headphones", 50.0, 30));
        products.add(new Product(4, "Smartwatch", 150.0, 15));
    }

    private static void viewProducts() {
        System.out.println("\nAvailable Products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void addToCart(Scanner scanner) {
        System.out.print("Enter Product ID to add to cart: ");
        int productId = scanner.nextInt();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        Product selectedProduct = null;
        for (Product product : products) {
            if (product.getId() == productId) {
                selectedProduct = product;
                break;
            }
        }

        if (selectedProduct != null) {
            if (selectedProduct.getQuantity() >= quantity) {
                selectedProduct.setQuantity(selectedProduct.getQuantity() - quantity);
                cart.addToCart(selectedProduct, quantity);
            } else {
                System.out.println("Insufficient stock!");
            }
        } else {
            System.out.println("Invalid Product ID!");
        }
    }

    private static void removeFromCart(Scanner scanner) {
        System.out.print("Enter Product ID to remove from cart: ");
        int productId = scanner.nextInt();
        CartItem removedItem = cart.removeFromCart(productId);
        if (removedItem != null) {
            Product product = removedItem.getProduct();
            product.setQuantity(product.getQuantity() + removedItem.getQuantity());
        }
    }
}