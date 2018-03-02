package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DynamicListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("DynamicListener Inited");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("DynamicListener Destoryed");
    }
}
