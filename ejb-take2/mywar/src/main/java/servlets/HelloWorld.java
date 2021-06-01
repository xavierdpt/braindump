package servlets;

import ejbs.MyEJBI;
import ejbs.NoInterface;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hw")
public class HelloWorld extends HttpServlet {

    // business interface
    @EJB
    MyEJBI myEjb;

    @EJB
    NoInterface noInterface;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Type", "text/plain");
        PrintWriter writer = resp.getWriter();
        writer.println(myEjb.getMessage());
        writer.println(noInterface.getMessage());

        SessionContext sessionContext = noInterface.getSessionContext();
        MyEJBI myEjb2 = (MyEJBI) sessionContext.lookup("myEjb");
        writer.println(myEjb2.getMessage());
    }
}
