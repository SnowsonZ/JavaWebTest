package cart.dao;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import bean.CartItem;
import bean.Product;
import cart.service.CartServiceImp;
import utils.Constant;

public class CartDaoImp implements CartDao {

    private final Log logger;
    private ArrayList<CartItem> carts;
    private final Gson gson;

    public CartDaoImp() {
        logger = LogFactory.getLog(CartServiceImp.class);
        gson = new Gson();
        JsonParser parser = new JsonParser();
        try {
            carts = gson.fromJson(parser.parse(new FileReader(Constant.DB_CART)),
                    new TypeToken<ArrayList<CartItem>>() {
                    }.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<CartItem> queryAll() {
        return carts;
    }

    @Override
    public void addByProductId(int quantity, Product p) {
        if (p == null) {
            logger.error("can not found product...");
            return;
        }
        if (!hasProduct(p.getId())) {
            Product product = null;
            product = new Product();
            product.setId(p.getId());
            product.setName(p.getName());
            product.setDescription(p.getDescription());
            product.setPrice(p.getPrice());
            carts.add(new CartItem(quantity, product));
        } else {
            for (CartItem cartItem : carts) {
                if (cartItem.getProduct().getId() == p.getId()) {
                    cartItem.setQuantity(cartItem.getQuantity() + quantity);
                    logger.debug("add product: " + p.getId() + " " + quantity + "successful");
                }
            }
        }
        saveData();
    }

    @Override
    public void subByProductId(long productId) {
        for (CartItem cartItem : carts) {
            if (cartItem.getProduct().getId() == productId) {
                if (cartItem.getQuantity() <= 1) {
                    deleteItem(productId);
                } else {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                }
            }
        }
        saveData();
    }

    @Override
    public void deleteItem(long productId) {
        for (CartItem cartItem : carts) {
            if (cartItem.getProduct().getId() == productId) {
                carts.remove(cartItem);
                logger.debug("delete cart of " + productId + "successful...");
            }
        }
    }

    private boolean hasProduct(long productId) {
        for (CartItem cartItem : carts) {
            if (cartItem.getProduct().getId() == productId) {
                return true;
            }
        }
        return false;
    }

    private void saveData() {
        JsonWriter jsonWriter = null;
        try {
            jsonWriter = new JsonWriter(new FileWriter(Constant.DB_CART));
            gson.toJson(carts, new TypeToken<CartItem>() {
            }.getType(), jsonWriter);
        } catch (IOException e) {
            logger.error("write data to cart.json failed...");
        } finally {
            try {
                jsonWriter.close();
            } catch (IOException e) {
                logger.error("jsonWriter close error...");
            }
        }
    }
}
