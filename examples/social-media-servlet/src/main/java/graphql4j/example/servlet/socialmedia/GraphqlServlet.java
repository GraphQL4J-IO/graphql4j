package graphql4j.example.servlet.socialmedia;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionResult;
import graphql4j.example.servlet.socialmedia.controller.GraphqlController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/graphql"}, loadOnStartup = 1)
public class GraphqlServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String payload = req.getReader().lines().collect(Collectors.joining());
        ObjectMapper mapper = new ObjectMapper();
        Map jsonPayload = mapper.readValue(payload, Map.class);
        ExecutionResult result = new GraphqlController().index((String)jsonPayload.get("query"));
        res.setContentType("application/json");
        res.getOutputStream().write(mapper.writeValueAsBytes(result));
    }
}
