package graphql4j;

import graphql.schema.GraphQLSchema;

public final class GraphqlBuilders {

    private GraphqlBuilders() {}

    public static GraphQLSchema.Builder buildSchema() {
        return GraphQLSchema.newSchema();
    }

}
