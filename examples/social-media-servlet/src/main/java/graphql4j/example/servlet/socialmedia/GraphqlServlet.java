package graphql4j.example.servlet.socialmedia;

import graphql4j.example.servlet.socialmedia.controller.GraphqlController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@WebServlet(urlPatterns = {"/graphql"}, loadOnStartup = 1)
public class GraphqlServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        super.doPost(req,res);
        System.out.println("-----------");

        for (String a : Collections.list(req.getParameterNames())) {
            System.out.println(a);
        }
        res.setContentType("application/json");
        res.getOutputStream().write(new GraphqlController().index(req.getParameter("query")).toString().getBytes());
        //res;
    }
}
