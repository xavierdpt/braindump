package ejbs;

import javax.ejb.Remote;

@Remote
public interface MyEJBI {
    String getMessage();
}
