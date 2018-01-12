package graphql4j.example.servlet.socialmedia.repository;

import graphql4j.example.servlet.socialmedia.Repository;
import graphql4j.example.servlet.socialmedia.model.Post;
import graphql4j.stereotype.GraphqlSchema;

@GraphqlSchema
public interface PostRepository extends Repository<Post, Long> {

}
