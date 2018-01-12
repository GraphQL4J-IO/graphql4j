package graphql4j.example.servlet.socialmedia;

import graphql4j.stereotype.GraphqlQuery;

public interface Repository<T, ID> {

    @GraphqlQuery
    T findById(ID id);

}
