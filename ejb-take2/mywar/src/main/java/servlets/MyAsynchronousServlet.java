package servlets;

import ejbs.MyAsynchronousBeanI;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

@WebServlet(urlPatterns = "/async", asyncSupported = true)
public class MyAsynchronousServlet extends HttpServlet {
    @EJB
    MyAsynchronousBeanI myAsynchronousBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Type", "text/plain");
        PrintWriter writer = resp.getWriter();
        try {
            String message = myAsynchronousBean.getMessage().get();
            writer.println(message);
        } catch (InterruptedException | ExecutionException e) {
            writer.println(e.getMessage());
        }
    }
}
