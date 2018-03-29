package graphql4j.blueprint;

import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

import java.util.HashSet;
import java.util.Set;

public class GraphqlSchema {

  private final Set<GraphQLFieldDefinition> queryFieldSet;
  private final Set<GraphQLFieldDefinition> mutationFieldSet;

  public GraphqlSchema() {
    this.queryFieldSet = new HashSet<>();
    this.mutationFieldSet = new HashSet<>();
  }

  public GraphqlSchema(GraphqlSchema schemaToMerge) {
    this();
    this.queryFieldSet.addAll(schemaToMerge.getQueryFieldSet());
    this.mutationFieldSet.addAll(schemaToMerge.getMutationFieldSet());
  }

  public Set<GraphQLFieldDefinition> getQueryFieldSet() {
    return queryFieldSet;
  }

  public Set<GraphQLFieldDefinition> getMutationFieldSet() {
    return mutationFieldSet;
  }

  public void addQueryField(GraphQLFieldDefinition queryField) {
    queryFieldSet.add(queryField);
  }

  public void addMutationField(GraphQLFieldDefinition mutationField) {
    mutationFieldSet.add(mutationField);
  }

  public GraphQLSchema build() {
    GraphQLSchema.Builder schemaBuilder = GraphQLSchema.newSchema();
    buildQuery(schemaBuilder);
    buildMutation(schemaBuilder);
    return schemaBuilder.build();
  }

  private void buildQuery(GraphQLSchema.Builder schemaBuilder) {
    if (queryFieldSet.isEmpty()) return;
    GraphQLObjectType.Builder queryBuilder = GraphQLObjectType.newObject().name("Query");
    for (GraphQLFieldDefinition queryFieldDef : queryFieldSet) {
      queryBuilder.field(queryFieldDef);
    }
    schemaBuilder.query(queryBuilder);
  }

  private void buildMutation(GraphQLSchema.Builder schemaBuilder) {
    if (mutationFieldSet.isEmpty()) return;
    GraphQLObjectType.Builder mutationBuilder = GraphQLObjectType.newObject().name("Mutation");
    for (GraphQLFieldDefinition mutationFieldDef : mutationFieldSet) {
      mutationBuilder.field(mutationFieldDef);
    }
    schemaBuilder.mutation(mutationBuilder);
  }

}
