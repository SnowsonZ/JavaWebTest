package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import utils.CodeHelper;

@WebServlet(name = "AttrbutesServlet", urlPatterns = "/attr",
        initParams = {
                @WebInitParam(name = "userName", value = "snowson"),
                @WebInitParam(name = "password", value = "123test")
        })
public class AttrbutesServlet implements Servlet {
    private ServletConfig servletConfig;

    /**
     * lifecycle 1
     *
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    /**
     * lifecycle 2
     *
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        StringBuilder params = new StringBuilder("");
        String userName = servletConfig.getInitParameter("userName");
        String password = servletConfig.getInitParameter("password");
//        ServletContext servletContext = servletConfig.getServletContext();

        if (!CodeHelper.isNullOfStr(userName)) {
            params.append(userName);
        }
        if (!CodeHelper.isNullOfStr(password)) {
            params.append(password);
        }
        PrintWriter writer = servletResponse.getWriter();
        servletResponse.setContentType("text/html");
        writer.print("<html>");
        writer.print("<body>");
        writer.print("<div>the user info is " + params + "</div>");
        writer.print("</body>");
        writer.print("</html>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * lifecycle 3
     */
    @Override
    public void destroy() {

    }


}
