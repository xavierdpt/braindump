package ejbs;

import ejbs.MyAsynchronousBeanI;

import javax.ejb.Stateful;
import javax.inject.Named;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Stateful
public class MyAsynchronousBean implements MyAsynchronousBeanI {

    @Named
    private ExecutorService executorService;

    @Override
    public Future<String> getMessage() {
        return executorService.submit(() -> {
            Thread.sleep(10000);
            return "Hello from the future";
        });
    }
}
