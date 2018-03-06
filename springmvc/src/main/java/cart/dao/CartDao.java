package cart.dao;

import java.util.ArrayList;

import bean.CartItem;
import bean.Product;

public interface CartDao {
    ArrayList<CartItem> queryAll();

    void addByProductId(int quantity, Product product);

    void subByProductId(long productId);

    void deleteItem(long productId);
}
