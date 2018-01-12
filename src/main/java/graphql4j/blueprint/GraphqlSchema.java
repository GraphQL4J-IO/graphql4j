package graphql4j.blueprint;

import graphql.schema.GraphQLObjectType;

public class GraphqlSchema {

    private final GraphQLObjectType query;
    private final GraphQLObjectType mutation;

    public GraphqlSchema(GraphQLObjectType query, GraphQLObjectType mutation) {
        this.query = query;
        this.mutation = mutation;
    }

    public GraphQLObjectType getQuery() {
        return query;
    }

    public GraphQLObjectType getMutation() {
        return mutation;
    }
}
