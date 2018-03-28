package graphql4j;

import graphql.Scalars;
import graphql.schema.GraphQLType;

public class GraphqlTypeResolver {

  public static GraphQLType resolve(Class javaType) {
    if (String.class.equals(javaType)) {
      return Scalars.GraphQLString;
    }
    throw new IllegalStateException("No type matches found.");
  }

}
