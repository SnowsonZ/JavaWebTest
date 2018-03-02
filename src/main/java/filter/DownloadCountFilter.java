package filter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.handler.LogicalHandler;

import utils.CodeHelper;

//@WebFilter(filterName = "DownloadCountFilter")
public class DownloadCountFilter implements Filter {
    ExecutorService service = Executors.newSingleThreadExecutor();
    private File logFile;
    private Properties logProperties;

    public void destroy() {
        if (service != null) {
            service.shutdown();
        }
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        service.execute(() -> {
            String property = logProperties.getProperty(uri);
            if (CodeHelper.isNullOfStr(property)) {
                logProperties.setProperty(uri, "1");
                System.out.println(uri + ": the first visit");
            } else {
                Integer count = Integer.valueOf(property);
                logProperties.setProperty(uri, String.valueOf(++count));
                System.out.println(uri + ": visit count " + count);
            }
            try {
                logProperties.store(new FileWriter(logFile), "visi count is change");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("DownloadFilter init");
        String path = config.getServletContext().getRealPath("/");
        logFile = new File(path, "downloadLog.txt");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logProperties = new Properties();
        try {
            logProperties.load(new FileReader(logFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
