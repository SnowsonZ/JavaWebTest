package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class ContextListener implements ServletContextListener, HttpSessionListener, ServletRequestListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext Created");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext Destroyed");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("HttpSession Created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("HttpSession Destoryed");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("HttpRequest Destoryed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("HttpRequest Created");
    }
}
