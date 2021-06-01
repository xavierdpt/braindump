package servlets;

import ejbs.interfaces.x.XI;
import ejbs.interfaces.y.YI;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/x")
public class X extends HttpServlet {

    @EJB
    private XI xi;

    @EJB
    private YI yi;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(xi.xfoo(yi));
    }
}
