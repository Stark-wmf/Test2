package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DemoServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String name = req.getParameter("name");
        int id = Integer.parseInt(req.getParameter("id"));

    }
}
