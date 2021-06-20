package ejbs;

import ejbs.MyVetoedEJBI;

import javax.ejb.Stateful;
import javax.enterprise.inject.Vetoed;

@Stateful
@Vetoed
public class MyVetoedEJB implements MyVetoedEJBI {
    @Override
    public String getMessage() {
        return "I should be vetoed";
    }
}
