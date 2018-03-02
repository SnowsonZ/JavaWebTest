package listener;

import java.io.IOException;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

public class TestAsyncListener implements AsyncListener {
    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        System.out.println("asyncListener complete");
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        System.out.println("asyncListener timeout");
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        System.out.println("asyncListener error : " + event.getThrowable().getMessage());
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        System.out.println("asyncListener startAsync");
    }
}
