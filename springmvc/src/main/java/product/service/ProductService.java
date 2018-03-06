package product.service;

import java.util.ArrayList;

import bean.Product;

public interface ProductService {

    ArrayList<Product> queryAll();

    void add(Product product);

    void getProductById(long productId);
}
