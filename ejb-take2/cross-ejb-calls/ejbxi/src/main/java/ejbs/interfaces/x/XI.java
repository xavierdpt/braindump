package ejbs.interfaces.x;


import ejbs.interfaces.y.YI;

import javax.ejb.Remote;

@Remote
public interface XI {
    String xfoo(YI yi);
}
