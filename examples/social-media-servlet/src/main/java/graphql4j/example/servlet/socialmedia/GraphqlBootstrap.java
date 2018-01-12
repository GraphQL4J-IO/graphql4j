package graphql4j.example.servlet.socialmedia;

import graphql4j.GraphqlScanner;
import graphql4j.blueprint.GraphqlSchema;

public class GraphqlBootstrap {

    public static GraphqlSchema init() {
        GraphqlScanner scanner = new GraphqlScanner();
        System.out.println("::::::::::::::: " + GraphqlServlet.class.getPackage().getName());
        return scanner.scan(GraphqlServlet.class.getPackage().getName());
    }

}
