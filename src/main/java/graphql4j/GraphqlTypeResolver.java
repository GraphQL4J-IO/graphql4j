package graphql4j;

import graphql.Scalars;
import graphql.schema.GraphQLOutputType;

public class GraphqlTypeResolver {

  public static GraphQLOutputType resolveGraphQLOutputType(Class javaType) {
    if (String.class.equals(javaType)) {
      return Scalars.GraphQLString;
    }
    throw new IllegalStateException("No type matches found.");
  }

}
