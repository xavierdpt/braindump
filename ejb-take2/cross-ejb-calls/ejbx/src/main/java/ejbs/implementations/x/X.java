package ejbs.implementations.x;

import ejbs.interfaces.x.XI;
import ejbs.interfaces.y.YI;

import javax.ejb.Stateless;

@Stateless
public class X implements XI {

    @Override
    public String xfoo(YI y) {
        return y.getMessage();
    }
}
