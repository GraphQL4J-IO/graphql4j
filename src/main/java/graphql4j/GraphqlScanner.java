package graphql4j;

import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql4j.blueprint.GraphqlSchema;
import graphql4j.stereotype.GraphqlIgnore;
import graphql4j.stereotype.GraphqlMutation;

import java.lang.reflect.Method;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.*;
import static graphql.schema.GraphQLObjectType.*;

public class GraphqlScanner {

  private GraphqlScanner() {}

  public static GraphqlSchema scan(Object object) {
    return scan(object, object.getClass());
  }

  public static GraphqlSchema scan(Object dataFetcherObject, Class schemaClass) {
    GraphqlSchema schema = new GraphqlSchema();
    for (Method method : schemaClass.getMethods()) {
      if (method.isAnnotationPresent(GraphqlIgnore.class)) continue;
      if (method.isAnnotationPresent(GraphqlMutation.class)) {
        schema.addMutationField(buildFieldDef(method));
      }
    }
    return schema;
  }

  private static GraphQLFieldDefinition buildFieldDef(Method method) {
    GraphQLFieldDefinition.Builder fieldDefBuilder = GraphQLFieldDefinition.newFieldDefinition();
    fieldDefBuilder.name(method.getName());
    fieldDefBuilder.type(GraphqlTypeResolver.resolveGraphQLOutputType(method.getReturnType()));
    return fieldDefBuilder.build();
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
