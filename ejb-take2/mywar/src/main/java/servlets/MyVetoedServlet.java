package servlets;

import ejbs.MyVetoedEJBI;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/vetoed")
public class MyVetoedServlet extends HttpServlet {

    @EJB
    private MyVetoedEJBI myVetoedEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Type", "text/plain");
        resp.getWriter().println(myVetoedEJB.getMessage());
    }
}
