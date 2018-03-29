package graphql4j;

import graphql.ExecutionInput;
import graphql.GraphQL;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLObjectType;
import graphql4j.blueprint.GraphqlSchema;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.*;
import static graphql.schema.GraphQLObjectType.*;

public class GraphqlServer {

  private final GraphQL graphQL;

  public GraphqlServer(GraphqlSchema graphqlSchema) {
    this.graphQL = GraphQL.newGraphQL(graphqlSchema.build()).build();
  }

  public GraphqlResult execute(String query) {
    ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query)
      .build();

    return GraphqlResult.from(graphQL.execute(executionInput));
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
