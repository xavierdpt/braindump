package ejbs;

import javax.ejb.Remote;

@Remote
public interface MyVetoedEJBI {
    String getMessage();
}
