package ejbs.implementations.y;

import ejbs.interfaces.y.YI;

import javax.ejb.Stateless;

@Stateless
public class Y implements YI {
    @Override
    public String getMessage() {
        return "Hello from Y";
    }
}
