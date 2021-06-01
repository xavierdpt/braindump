package ejbs;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
public class NoInterface {

    @Resource
    private SessionContext sessionContext;

    public String getMessage() {
        return "Hello from a no interface bean";
    }

    public SessionContext getSessionContext() {
        return sessionContext;
    }
}
