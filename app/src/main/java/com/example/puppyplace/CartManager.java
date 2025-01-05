package com.example.puppyplace;

import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private static CartManager instance;
    private List<Item> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addItem(Item item) {
        cartItems.add(item);
    }

    public void removeItem(Item item) {
        cartItems.remove(item);
    }

    public List<Item> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public void clearCart() {
        cartItems.clear();
    }
}
