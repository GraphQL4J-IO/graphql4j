package graphql4j.example.servlet.socialmedia;

import graphql4j.example.servlet.socialmedia.model.Author;
import graphql4j.example.servlet.socialmedia.model.Post;

import java.util.HashMap;
import java.util.Map;

public final class Datasource {

    private static final Map<Long, Author> authorMap = new HashMap<>();
    private static final Map<Long, Post> postMap = new HashMap<>();

    public static void init() {
        Author author = new Author(1L, "Akter", "Hossain");
        Post post1 = new Post(1l, "My First Post", "Body of my first post", author);
        Post post2 = new Post(2l, "My Second Post", "Body of my second post", author);
        authorMap.put(author.getId(), author);
        postMap.put(post1.getId(), post1);
        postMap.put(post2.getId(), post2);
    }

}
