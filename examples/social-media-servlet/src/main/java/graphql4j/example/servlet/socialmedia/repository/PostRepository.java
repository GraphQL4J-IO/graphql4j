package graphql4j.example.servlet.socialmedia.repository;

import graphql4j.example.servlet.socialmedia.Repository;
import graphql4j.example.servlet.socialmedia.model.Post;

public class PostRepository implements Repository<Post, Long> {

    @Override
    public Post findById(Long id) {
        return null;
    }
}
