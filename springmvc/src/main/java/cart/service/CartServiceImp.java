package cart.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

import bean.CartItem;
import bean.Product;
import cart.dao.CartDaoImp;
import product.Dao.ProductDaoImp;

@Service
public class CartServiceImp implements CartService {

    private final CartDaoImp cartDaoImp;
    private final ProductDaoImp productDaoImp;

    public CartServiceImp() {
        cartDaoImp = new CartDaoImp();
        productDaoImp = new ProductDaoImp();
    }

    @Override
    public void add(long productId, int quantity) {
        Product p = productDaoImp.queryByProductId(productId);
        Product product = new Product();
        product.setId(p.getId());
        product.setName(p.getName());
        product.setDescription(p.getDescription());
        product.setPrice(p.getPrice());
        cartDaoImp.addByProductId(quantity, product);
    }

    @Override
    public void sub(long productId) {
        cartDaoImp.subByProductId(productId);
    }

    @Override
    public void delete(long productId) {
        cartDaoImp.deleteItem(productId);
    }

    @Override
    public ArrayList<CartItem> queryAll() {
        return cartDaoImp.queryAll();
    }

    public void add(long productId) {
        add(productId, 1);
    }
}
