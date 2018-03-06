package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HellowWorldServlet", urlPatterns = {"/hellowWorld"})
public class HellowWorldServlet implements Servlet {
    private transient ServletConfig servletConfig;


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException {
        String servletName = servletConfig.getServletName();
        servletResponse.setContentType("text/html");
        //输出二进制数据
//        servletResponse.getOutputStream();
        //默认使用ISO-8859-1编码
        PrintWriter writer = servletResponse.getWriter();
        writer.print("<html>");
//        writer.print("<head>" + getServletInfo() + "</head>");
        writer.print("<body>Hello From" + servletName + "</body>");
        writer.print("</html>");
    }

    @Override
    public String getServletInfo() {
        return "Hello World !!!";
    }

    @Override
    public void destroy() {

    }
}
