import java.util.HashMap;

public class Cart {
    private final HashMap<Integer, CartItem> cartItems;

    public Cart() {
        cartItems = new HashMap<>();
    }

    public synchronized void addToCart(Product product, int quantity) {
        if (cartItems.containsKey(product.getId())) {
            CartItem existingItem = cartItems.get(product.getId());
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            cartItems.put(product.getId(), new CartItem(product, quantity));
        }
        System.out.println(quantity + " units of " + product.getName() + " added to the cart.");
    }

    public synchronized CartItem removeFromCart(int productId) {
        if (cartItems.containsKey(productId)) {
            CartItem removedItem = cartItems.remove(productId);
            System.out.println("Product removed from the cart.");
            return removedItem;
        } else {
            System.out.println("Product not found in the cart.");
            return null;
        }
    }

    public synchronized void viewCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Your Cart:");
            for (CartItem item : cartItems.values()) {
                System.out.println(item);
            }
        }
    }

    public synchronized void checkout() {
        double totalAmount = 0;
        System.out.println("\nCheckout Summary:");
        for (CartItem item : cartItems.values()) {
            System.out.println(item);
            totalAmount += item.getTotalPrice();
        }
        System.out.println("Total Amount: $" + totalAmount);
        cartItems.clear();
        System.out.println("Thank you for your purchase!");
    }
}