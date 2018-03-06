package product.Dao;

import java.util.ArrayList;

import bean.Product;

public interface ProductDao {
    ArrayList<Product> queryAll();

    void add(Product product);

    Product queryByProductId(long productId);
}
