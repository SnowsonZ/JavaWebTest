package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Customer;

/**
 * 隐藏域实现会话保存
 */
public class HiddenDomainServlet extends HttpServlet {

    private List<Customer> customers = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("Donald D.");
        customer1.setCity("Miami");
        customers.add(customer1);
        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setName("Mickey M.");
        customer2.setCity("Orlando");
        customers.add(customer2);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        Customer customer = getCustomerById(id);
        if (customer != null) {
            customer.setName(name);
            customer.setCity(city);
        }
        response.sendRedirect("customer");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (requestURI.endsWith("customer")) {
            sendCustomerList(response);
        } else if (requestURI.endsWith("customerEdit")) {
            sendCustomerEdit(request, response);
        }
    }

    private void sendCustomerEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        Integer customerId = Integer.valueOf(request.getParameter("customerId"));
        Customer customer = getCustomerById(customerId);
        if (customer == null) {
            writer.println("No customer found!!");
        } else {
            writer.println("<html><head><title>Customers Edit</title></head>"
                    + "<body><h2>Customers Edit</h2>"
                    // form action：向何处提交表单数据
                    + "<form method='post' action='customer'>");
            writer.println("<input type='hidden' name='id' value='" + customerId + "' />");
            writer.println("<table>");
            writer.println("<tr><td>Name:</td><td>"
                    + "<input name='name' value='" +
                    customer.getName().replaceAll("'", "&#39;")
                    + "'/></td></tr>");
            writer.println("<tr><td>City:</td><td>"
                    + "<input name='city' value='" +
                    customer.getCity().replaceAll("'", "&#39;")
                    + "'/></td></tr>");
            writer.println("<tr>" + "<td colspan='2' style='text-align:right'>"
                    + "<input type='submit' value='Update'/></td>"
                    + "</tr>");
            writer.println("<tr><td colspan='2'>"
                    + "<a href='customer'>Customer List</a>"
                    + "</td></tr>");
            writer.println("</table></form></body></html>");
        }
    }

    private Customer getCustomerById(Integer customerId) {
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    private void sendCustomerList(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><title>Customers</title></head>" + "<body><h2>Customers </h2>");
        writer.println("<ul>");
        for (Customer customer : customers) {
            writer.println("<li>");
            writer.print(customer.getName() + "(" + customer.getCity() + ")");
            writer.print("(<a href='customerEdit?customerId=" + customer.getId() + "'>edit</a>)");
            writer.println("</li>");
        }
        writer.println("</ul>");
    }
}
