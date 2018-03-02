package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import listener.TestAsyncListener;

//@WebServlet(name = "TestAsyncServlet", asyncSupported = true)
public class TestAsyncServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("UTF-8");
        PrintWriter writer = response.getWriter();
        AsyncContext asyncContext = request.startAsync();
        asyncContext.setTimeout(10000);
        asyncContext.addListener(new TestAsyncListener());
        writer.println("<html><head><title>TestAsyncServlet</title></head>");
        writer.println("<body>");
        writer.println("<a id='content'></a>");
        asyncContext.start(() -> {
            for (int i = 0; i < 20; i++) {
                writer.println("<script>");
                writer.println("document.getElementById('content').innerHTML = "
                        + "'service complete is " + i * 5 + "'");
                writer.println("</script>");
                //TODO 第一次请求会有一个NullPointer Exception
                writer.flush();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            writer.println("<script>");
            writer.println("document.getElementById('content').innerHTML = 'service complete done'");
            writer.println("</script>");
            writer.println("</body></html>");
            asyncContext.complete();
        });
    }
}
