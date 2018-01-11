package graphql4j.example.servlet.socialmedia;

public interface Repository<T, ID> {

    T findById(ID id);

}
