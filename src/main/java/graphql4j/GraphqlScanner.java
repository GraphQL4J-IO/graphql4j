package graphql4j;

import graphql4j.blueprint.GraphqlSchema;

import java.lang.reflect.Method;

public class GraphqlScanner {

  private GraphqlScanner() {}

  public static GraphqlSchema scan(Object object) {
    for (Method method : object.getClass().getMethods()) {
      System.out.println(method);
    }
    return null;
  }

}
