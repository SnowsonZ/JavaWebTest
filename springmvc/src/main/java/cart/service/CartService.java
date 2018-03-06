package cart.service;

import java.util.ArrayList;

import bean.CartItem;

public interface CartService {
    void add(long productId, int quantity);

    void sub(long productId);

    void delete(long cartId);

    ArrayList<CartItem> queryAll();
}
