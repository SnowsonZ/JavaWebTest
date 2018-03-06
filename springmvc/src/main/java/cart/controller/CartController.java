package cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import bean.CartItem;
import cart.service.CartService;

@Controller("/product")
public class CartController {

    @Autowired
    private CartService cartServiceImp;

    @RequestMapping("/list")
    public void getAllProductsInCart(HttpSession session) {
        ArrayList<CartItem> cartItems = cartServiceImp.queryAll();
        if (session != null) {
            session.setAttribute("carts", cartItems);
        }
    }

}
