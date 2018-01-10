package graphql4j.example.servlet.socialmedia.controller;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.Scalars;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

import java.util.List;

import static graphql.schema.GraphQLFieldDefinition.*;
import static graphql.schema.GraphQLObjectType.*;

public class GraphqlController {

    public ExecutionResult index(String query) {
        System.out.println("::: " + query);
        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(getQueryType())
                .build();

        GraphQL graphQL = GraphQL.newGraphQL(schema)
                .build();

        ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query)
                .build();

        ExecutionResult executionResult = graphQL.execute(executionInput);

        Object data = executionResult.getData();
        List<GraphQLError> errors = executionResult.getErrors();
        return executionResult;
    }

    public GraphQLObjectType getQueryType() {
        return newObject()
                .name("Query")
                .field(newFieldDefinition()
                        .name("findPerson")
                        .type(Scalars.GraphQLString)
                        .build())
                .build();
    }

}
