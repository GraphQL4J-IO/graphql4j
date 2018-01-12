package graphql4j;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql4j.blueprint.GraphqlSchema;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.*;
import static graphql.schema.GraphQLObjectType.*;

public class GraphqlController {

    private final GraphQL graphQL;

    public GraphqlController(GraphqlSchema graphqlSchema) {
        this.graphQL = GraphQL
                .newGraphQL(GraphQLSchema.newSchema()
                        .query(graphqlSchema.getQuery())
                        .mutation(graphqlSchema.getMutation())
                        .build())
                .build();
    }

    public ExecutionResult index(String query) {
        ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query)
                .build();

        ExecutionResult executionResult = graphQL.execute(executionInput);

        //Object data = executionResult.getData();
        //List<GraphQLError> errors = executionResult.getErrors();
        return executionResult;
    }

    public GraphQLObjectType getQueryType() {
        return newObject()
                .name("Query")
                .field(newFieldDefinition()
                        .name("findPerson")
                        .type(GraphQLString)
                        .argument(GraphQLArgument.newArgument()
                                .name("id")
                                .type(GraphQLString)
                                .build())
                        .build())
                .build();
    }

}
