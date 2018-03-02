package container;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

import servlet.DynamicServlet;

/**
 * 自定义servlet容器加载器
 */
@HandlesTypes({DynamicServlet.class})
public class TestContainer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
//        ServletRegistration.Dynamic dynamicServlet
//                = ctx.addServlet("dynamicServlet", DynamicServlet.class);
//        dynamicServlet.addMapping("/dynamic");
        System.out.println("TestContainer");
    }
}
