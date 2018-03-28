package graphql4j.blueprint;

import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

import java.util.HashSet;
import java.util.Set;

public class GraphqlSchema {

  private static final GraphqlSchema singleton = new GraphqlSchema();

  private final Set<GraphQLFieldDefinition> queryFieldSet;
  private final Set<GraphQLFieldDefinition> mutationFieldSet;

  private GraphqlSchema() {
    this.queryFieldSet = new HashSet<>();
    this.mutationFieldSet = new HashSet<>();
  }

  public GraphQLSchema build() {
    GraphQLObjectType.Builder queryBuilder = GraphQLObjectType.newObject().name("Query");
    for (GraphQLFieldDefinition queryFieldDef : queryFieldSet) {
      queryBuilder.field(queryFieldDef);
    }
    GraphQLObjectType.Builder mutationBuilder = GraphQLObjectType.newObject().name("Mutation");
    for (GraphQLFieldDefinition mutationFieldDef : mutationFieldSet) {
      mutationBuilder.field(mutationFieldDef);
    }
    return GraphQLSchema.newSchema()
      .query(queryBuilder.build())
      .mutation(mutationBuilder.build())
      .build();
  }

  public void addQueryField(GraphQLFieldDefinition queryField) {
    queryFieldSet.add(queryField);
  }

  public void addMutationField(GraphQLFieldDefinition mutationField) {
    mutationFieldSet.add(mutationField);
  }

  public static GraphqlSchema instance() {
    return singleton;
  }

}
