package product.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

import bean.Product;
import product.Dao.ProductDaoImp;
@Service
public class ProductServiceImp implements ProductService {


    private final ProductDaoImp productDaoImp;

    public ProductServiceImp() {
        productDaoImp = new ProductDaoImp();
    }

    @Override
    public ArrayList<Product> queryAll() {
        return productDaoImp.queryAll();
    }

    @Override
    public void add(Product product) {
        productDaoImp.add(product);
    }

    @Override
    public void getProductById(long productId) {
        productDaoImp.queryByProductId(productId);
    }
}
