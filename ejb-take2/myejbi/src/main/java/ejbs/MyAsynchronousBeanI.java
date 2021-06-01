package ejbs;

import javax.ejb.Remote;
import java.util.concurrent.Future;

@Remote
public interface MyAsynchronousBeanI {
    Future<String> getMessage();
}
