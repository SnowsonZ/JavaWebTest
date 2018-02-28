package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartItem;
import bean.Product;
import utils.CodeHelper;

public class SessionServlet extends HttpServlet {
    private List<Product> products = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        products.add(new Product(1, "Bravo 32' HDTV", "Low-cost HDTV from renowned TV manufacturer",
                159.95F));
        products.add(new Product(2, "Bravo BluRay Player",
                "High quality stylish BluRay player", 99.95F));
        products.add(new Product(3, "Bravo Stereo System",
                "5 speaker hifi system with iPod player",
                129.95F));
        products.add(new Product(4, "Bravo iPod player",
                "An iPod plug-in that can play multiple formats", 39.95F));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId_str = request.getParameter("productId");
        String quantity_str = request.getParameter("quantity");
        int productId, quantity;
        if (CodeHelper.isNullOfStr(productId_str) || CodeHelper.isNullOfStr(quantity_str)) {
            productId = 0;
            quantity = 0;
        } else {
            productId = Integer.valueOf(productId_str);
            quantity = Integer.valueOf(quantity_str);
            CartItem item = new CartItem(quantity, getProduct(productId));
            List<CartItem> carts = new ArrayList<>();
            carts.add(item);
            HttpSession session = request.getSession();
            session.setAttribute("cart", carts);
        }
        sendProductList(response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (requestURI.endsWith("products")) {
            sendProductList(response);
        } else if (requestURI.endsWith("productDetail")) {
            sendProductDetails(request, response);
        } else if (requestURI.endsWith("cart")) {
            sendCart(request, response);
        } else if (requestURI.endsWith("addToCart")) {
            sendAddToCart(response);
        }
    }

    private void sendAddToCart(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><title>添加成功页面</title></head>");
        writer.println("<body>");
        writer.println("<h1>添加购物车成功页面</h1>");
        writer.println("<a href='products'>View ProductList</a>");
        writer.println("</body></html>");
    }

    private void sendCart(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();
        Object cart = session.getAttribute("cart");
        if (cart != null) {
            ArrayList<CartItem> carts = (ArrayList<CartItem>) cart;
            writer.println("<html><head><title>购物车页面</title></head>");
            writer.println("<body>");
            writer.println("<h1>购物车页面</h1>");
            writer.println("<ul>");
            for (CartItem item : carts) {
                writer.println("<li>" + item.getProduct().getName() + " " + item.getProduct().getDescription()
                        + " " + item.getProduct().getPrice() + " " + item.getQuantity() + "</li>");
            }
            writer.println("</ul>");
            writer.println("</br>");
            writer.println("<a href='products'>View ProductList</a>");
            writer.println("</body></html>");
        } else {
            writer.println("Cart is empt!!!");
        }

    }


    private void sendProductDetails(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        int productId = Integer.valueOf(request.getParameter("productId"));
        Product product = getProduct(productId);
        if (product == null) {
            writer.println("Product Cannot Found");
        } else {
            writer.println("<html><head><title>商品详情页面</title></head>");
            writer.println("<body><form method='post'>");
            writer.println("<table>");
            writer.println("<h1>商品详情</h1>");
            writer.println("<tr>");
            writer.println("<td>Name:</td> <td>" + product.getName() + "</td>");
            writer.println("</tr>");
            writer.println("<tr>");
            writer.println("<td>Description:</td> <td>" + product.getDescription() + "</td>");
            writer.println("</tr>");
            writer.println("<tr>");
            writer.println("<td><input name='quantity' /></td>");
            writer.println("<td><input type='submit' value='Buy'/></td>");
            writer.println("</tr>");
            writer.println("</table>");
            writer.println("</form></body></html>");
        }
    }

    private Product getProduct(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    private void sendProductList(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><title>商品列表页面</title></head>");
        writer.println("<body>");
        writer.println("<h1>商品列表</h1>");
        writer.println("<ul>");
        for (Product product : products) {
            writer.println("<li>" + product.getName() + " " + product.getDescription()
                    + "(" + product.getPrice() + ")(<a href='productDetail?productId="
                    + product.getId() + "'>detail</a>)</li>");
        }
        writer.println("</ul>");
        writer.println("</br>");
        writer.println("<a href='cart'>View Cart</a>");
        writer.println("</body></html>");
    }
}
