package graphql4j.example.servlet.socialmedia;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql4j.GraphqlResult;
import graphql4j.GraphqlServer;

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
    public void init() throws ServletException {
        super.init();
        Datasource.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String payload = req.getReader().lines().collect(Collectors.joining());
        ObjectMapper mapper = new ObjectMapper();
        Map jsonPayload = mapper.readValue(payload, Map.class);
        GraphqlServer graphqlServer = new GraphqlServer(GraphqlBootstrap.init());
        GraphqlResult result = graphqlServer.execute((String)jsonPayload.get("query"));
        res.setContentType("application/json");
        res.getOutputStream().write(mapper.writeValueAsBytes(result));
    }
}
