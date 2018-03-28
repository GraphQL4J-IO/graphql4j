package graphql4j.example.servlet.socialmedia;

import graphql4j.GraphqlScanner;
import graphql4j.blueprint.GraphqlSchema;
import graphql4j.example.servlet.socialmedia.repository.PostRepository;

public class GraphqlBootstrap {

    public static GraphqlSchema init() {
        return GraphqlScanner.scan(PostRepository.class);
    }

}
