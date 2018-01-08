package graphql4j;

import graphql.ExecutionResult;
import graphql.GraphQLError;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class GraphqlResult implements ExecutionResult {
    private final Object data;
    private final List<GraphQLError> errors;
    private final transient boolean dataPresent;
    private final transient Map<Object, Object> extensions;

    public GraphqlResult(GraphQLError error) {
        this(false, null, Collections.singletonList(error), null);
    }

    public GraphqlResult(List<? extends GraphQLError> errors) {
        this(false, null, errors, null);
    }

    public GraphqlResult(Object data, List<? extends GraphQLError> errors) {
        this(true, data, errors, null);
    }

    public GraphqlResult(Object data, List<? extends GraphQLError> errors, Map<Object, Object> extensions) {
        this(true, data, errors, extensions);
    }

    private GraphqlResult(boolean dataPresent, Object data, List<? extends GraphQLError> errors, Map<Object, Object> extensions) {
        this.dataPresent = dataPresent;
        this.data = data;
        this.errors = Collections.unmodifiableList(
                errors == null || errors.isEmpty() ? Collections.emptyList() : errors);
        this.extensions = extensions;
    }

    @Override
    public <T> T getData() {
        //noinspection unchecked
        return (T) data;
    }

    @Override
    public List<GraphQLError> getErrors() {
        return errors;
    }

    @Override
    public Map<Object, Object> getExtensions() {
        return extensions;
    }

    @Override
    public Map<String, Object> toSpecification() {
        Map<String, Object> result = new LinkedHashMap<>();
        if (dataPresent) {
            result.put("data", data);
        }
        if (errors != null && !errors.isEmpty()) {
            result.put("errors", errorsToSpec(errors));
        }
        if (extensions != null) {
            result.put("extensions", extensions);
        }
        return result;
    }

    private Object errorsToSpec(List<GraphQLError> errors) {
        return errors.stream().map(GraphQLError::toSpecification).collect(toList());
    }

    @Override
    public String toString() {
        return "GraphqlResult {" +
                "data=" + data +
                ",\n errors=" + errors +
                ",\n dataPresent=" + dataPresent +
                ",\n extensions=" + extensions +
                '}';
    }
}
