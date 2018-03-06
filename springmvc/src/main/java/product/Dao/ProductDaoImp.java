package product.Dao;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import bean.Product;
import utils.Constant;

public class ProductDaoImp implements ProductDao {

    private Gson gson;
    private ArrayList<Product> products;
    private final Log logger;

    public ProductDaoImp() {
        logger = LogFactory.getLog(ProductDaoImp.class);
        gson = new Gson();
        JsonParser parser = new JsonParser();
        try {
            products = gson.fromJson(parser.parse(new FileReader(Constant.DB_PRODUCT)),
                    new TypeToken<ArrayList<Product>>() {
                    }.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Product> queryAll() {
        return products;
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public Product queryByProductId(long productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        logger.error("can not found the product");
        return null;
    }
}
